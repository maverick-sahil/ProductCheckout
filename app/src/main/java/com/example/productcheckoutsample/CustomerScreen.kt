package com.example.productcheckoutsample

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun CustomerScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val viewModel: MainViewModel = viewModel()
    val customers = viewModel.customers

    LazyColumn(
        modifier = modifier,
    ) {
        items(customers) { customer ->
            CustomerListItem(
                navController = navController,
                customer = customer
            )
            Divider(modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun CustomerListItem(
    navController: NavController,
    customer: Customer
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate(Screen.ProductScreen.withArgs(customer.name))
            }
    ) {
        Text(
            text = customer.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            color = Color.Black
        )
        Text(
            text = "Special Pricing Rule: ${customer.specialPricingRule.name}",
            fontSize = 14.sp,
            modifier = Modifier.fillMaxWidth(),
            color = Color.Gray
        )
    }
}