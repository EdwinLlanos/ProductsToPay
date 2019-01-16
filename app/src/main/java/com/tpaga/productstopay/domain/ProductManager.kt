package com.tpaga.productstopay.domain

import android.os.Build
import com.tpaga.productstopay.presentation.products.model.request.PurchaseEntity

object ProductManager {

    private var products = listOf(
        Product("1", "nameuno", "1000"),
        Product("2", "name dos", "2000"),
        Product("3", "name tres", "3000"),
        Product("4", "name cuatro", "4000")
    )

    fun getProducts(): List<Product> {
        return products
    }
}

fun Product.getProductToPay(): PurchaseEntity {

    return PurchaseEntity(
        "1000",
        "2019-11-05T20:10:57.549653+00:00",
        "${System.currentTimeMillis()}",
        id,
        "Descripción de la compra",
        "https://www.ptpay.com/product/${this.id}",
        listOf(this),
        Build.ID,
        "10.14.2.162",
        null
    )
}
