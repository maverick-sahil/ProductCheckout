package com.example.productcheckoutsample

sealed class Screen(val route: String) {
    object CustomerScreen : Screen("customer_screen")
    object ProductScreen : Screen("product_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { args ->
                append("/$args")
            }
        }
    }
}
