package com.tpaga.productstopay.domain

object ProductsManager {

    private var products = listOf(
        Product("name uno", "1000"),
        Product("name dos", "2000"),
        Product("name tres", "3000"),
        Product("name cuatro", "4000")
    )

    fun getProducts(): List<Product> {
        return products
    }
}