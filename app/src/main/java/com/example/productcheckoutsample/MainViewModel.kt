package com.example.productcheckoutsample

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val customers = listOf(
        Customer("Default", SpecialPricingRule.NONE),
        Customer("Microsoft", SpecialPricingRule.MICROSOFT),
        Customer("Amazon", SpecialPricingRule.AMAZON),
        Customer("Facebook", SpecialPricingRule.FACEBOOK)
    )

    val products = listOf(
        Item("Small Pizza", "10'' pizza for one person", 11.99),
        Item("Medium Pizza", "12'' Pizza for two persons", 15.99),
        Item("Large Pizza", "15'' Pizza for four persons", 21.99)
    )

    fun calculateTotal(customer: Customer, cartItems: List<CartItem>) : Double {
        val co = Checkout(pricingRules)
        cartItems.forEach { cartItem ->
            repeat(cartItem.quantity) {
                co.add(cartItem.item)
            }
        }
        return co.total(customer)
    }

    fun findCustomerByName(customers: List<Customer>, name: String?): Customer? {
        return customers.find { it.name == name }
    }
}