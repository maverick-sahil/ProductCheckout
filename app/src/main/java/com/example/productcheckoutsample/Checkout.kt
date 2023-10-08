package com.example.productcheckoutsample

class Checkout(private val pricingRules: List<PricingRule>) {
    private val cart = mutableListOf<Item>()
    fun add(item: Item) {
        cart.add(item)
    }

    fun total(customer: Customer): Double {
        var total = 0.0
        for (item in cart) {
            val customerName = customer.name
            val rule = pricingRules.find {
                (it.customerName == customerName || it.customerName == "Default") &&
                    (it.itemName == item.name || it.itemName == null)
            }
            val discountPercentage = rule?.discountPercentage ?: 0.0
            total += item.price * (1.0 - discountPercentage / 100.0)
        }
        return total
    }
}
