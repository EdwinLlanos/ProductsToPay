package com.tpaga.productstopay.domain

import android.os.Build
import com.tpaga.productstopay.presentation.productlist.model.request.PurchaseEntity

object ProductManager {

    private var products = listOf(
        Product("nameuno", "1000"),
        Product("name dos", "2000"),
        Product("name tres", "3000"),
        Product("name cuatro", "4000")
    )

    fun getProducts(): List<Product> {
        return products
    }
}

fun Product.getProductToPay(): PurchaseEntity {
    return PurchaseEntity(
        "1000",
        "2019-11-05T20:10:57.549653+00:00",
        "22340sfcasdfay3dfsdf62",
        "472",
        "Descripción de la compra",
        "https://www.ptpay.com/product/${this.name}",
        listOf(this),
        Build.ID,
        "10.14.2.162",
        null
    )
}
