package com.example.productcheckoutsample

data class PricingRule(
    val customerName: String,
    val itemName: String?,
    val discountPercentage: Double,
    val minimumQuantity: Int = 1
)

val pricingRules = listOf(
    PricingRule("Microsoft", "Small Pizza", 33.33, 3), // 3 for 2 deal (33.33% discount)
    PricingRule("Amazon", "Large Pizza", 9.10), // Discounted price for Large Pizza
    PricingRule("Facebook", "Medium Pizza", 20.0, 5)  // 5 for 4 deal (20% discount)
)
