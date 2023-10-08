package com.example.productcheckoutsample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ProductScreen(customerName: String?) {
    val cart = remember { mutableStateListOf<CartItem>() }
    var showDialog by remember { mutableStateOf(false) }
    val viewModel: MainViewModel = viewModel()
    val selectedCustomer = viewModel.findCustomerByName(viewModel.customers, customerName)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Customer: ${selectedCustomer?.name}",
            fontSize = 18.sp,
            modifier = Modifier.padding(5.dp)
        )
        Box(
            modifier = Modifier.weight(1f)
        ) {
            ProductList(viewModel.products, cart)
            Spacer(modifier = Modifier.height(16.dp))
        }
        selectedCustomer?.let { customer ->
            CustomAlertDialog(
                openDialog = showDialog,
                onCloseDialog = { showDialog = false },
                cartItems = cart.toList(),
                viewModel = viewModel,
                customer = customer
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            var totalQuantity = 0
            var totalPrice = 0.0
            cart.toList().forEach { cartItem ->
                totalQuantity += cartItem.quantity
                repeat(cartItem.quantity) {
                    totalPrice += cartItem.item.price
                }
            }
            Column {
                Text(
                    text = "Items: $totalQuantity",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    text = "Total: $${String.format("%.2f", totalPrice).toDouble()}",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
            }
            Button(
                onClick = {
                    showDialog = !showDialog
                },
                modifier = Modifier
                    .width(150.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "Go to cart")
            }
        }
    }
}

@Composable
fun ProductList(items: List<Item>, cart: MutableList<CartItem>) {
    LazyColumn {
        items(items) { item ->
            ProductItem(item, cart)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun ProductItem(item: Item, cart: MutableList<CartItem>) {
    var quantity by remember { mutableIntStateOf(0) }
    val existingCartItem = cart.find { it.item == item }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(
                    text = "$${item.price}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Text(
                text = item.description,
                fontSize = 14.sp,
                color = Color.Black
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {
                        quantity++
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add to Cart",
                        tint = Color.Black
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Quantity: $quantity",
                        modifier = Modifier.padding(end = 8.dp),
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                    OutlinedButton(
                        onClick = {
                            if (existingCartItem != null) {
                                cart.remove(existingCartItem)
                                if (quantity > 0) {
                                    existingCartItem.quantity += quantity
                                    quantity = 0
                                    cart.add(existingCartItem)
                                }
                            } else {
                                if (quantity > 0) {
                                    cart.add(CartItem(item, quantity))
                                    quantity = 0
                                }
                            }
                        }
                    ) {
                        Text(text = "Add to Cart", color = Color.Black)
                    }
                }
            }
        }
    }
}

@Composable
fun CustomAlertDialog(
    openDialog: Boolean,
    onCloseDialog: () -> Unit,
    cartItems: List<CartItem>,
    viewModel: MainViewModel,
    customer: Customer
) {
    if (openDialog) {
        Dialog(
            onDismissRequest = { onCloseDialog() }
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Cart Items (Discount applied if any)",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    LazyColumn {
                        items(cartItems) { item ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = item.item.name,
                                    fontSize = 16.sp,
                                    modifier = Modifier
                                        .padding(vertical = 8.dp)
                                )
                                Text(
                                    text = "${item.quantity}",
                                    fontSize = 16.sp,
                                    modifier = Modifier
                                        .padding(vertical = 8.dp)
                                )
                            }
                            Divider()
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Total Price: ${
                            String.format("%.2f", viewModel.calculateTotal(customer, cartItems)).toDouble()
                        }",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            onCloseDialog()
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = "Close")
                    }
                }
            }
        }
    }
}