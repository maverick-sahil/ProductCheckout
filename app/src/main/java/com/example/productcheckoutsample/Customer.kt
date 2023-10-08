package com.example.productcheckoutsample

data class Customer(
    val name: String,
    val specialPricingRule: SpecialPricingRule
)

enum class SpecialPricingRule {
    NONE,
    MICROSOFT,
    AMAZON,
    FACEBOOK
}