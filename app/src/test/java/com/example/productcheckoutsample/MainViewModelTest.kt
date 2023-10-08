package com.example.productcheckoutsample

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MainViewModelTest {
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel()
    }

    @Test
    fun testCalculateTotal_DefaultCustomer() {
        val customer = viewModel.findCustomerByName(viewModel.customers, "Default")
        val cartItems = listOf(
            CartItem(viewModel.products[0], 2),
            CartItem(viewModel.products[1], 3)
        )

        val total = viewModel.calculateTotal(customer!!, cartItems)
        // Small Pizza (2 * 11.99) + Medium Pizza (3 * 15.99) = 71.95
        assertEquals(71.95, total, 0.01)
    }

    @Test
    fun testCalculateTotal_MicrosoftCustomer() {
        val customer = viewModel.findCustomerByName(viewModel.customers, "Microsoft")
        val cartItems = listOf(
            CartItem(viewModel.products[0], 3),
            CartItem(viewModel.products[1], 2)
        )

        val total = viewModel.calculateTotal(customer!!, cartItems)
        // Small Pizza (3 * 11.99) - 1 Small Pizza = 23.98
        // Medium Pizza (2 * 15.99) = 31.98
        // Total = 55.96
        assertEquals(55.96, total, 0.01)
    }

    @Test
    fun testCalculateTotal_AmazonCustomer() {
        val customer = viewModel.findCustomerByName(viewModel.customers, "Amazon")
        val cartItems = listOf(
            CartItem(viewModel.products[2], 4)
        )

        val total = viewModel.calculateTotal(customer!!, cartItems)
        // Large Pizza (4 * 19.99) = 79.96
        assertEquals(79.96, total, 0.01)
    }

    @Test
    fun testCalculateTotal_FacebookCustomer() {
        val customer = viewModel.findCustomerByName(viewModel.customers, "Facebook")
        val cartItems = listOf(
            CartItem(viewModel.products[1], 5)
        )

        val total = viewModel.calculateTotal(customer!!, cartItems)
        // Medium Pizza (5 * 15.99) - 1 Medium Pizza = 63.96
        assertEquals(63.96, total, 0.01)
    }
}
