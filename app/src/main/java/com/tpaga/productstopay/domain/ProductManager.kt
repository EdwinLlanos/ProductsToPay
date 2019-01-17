package com.tpaga.productstopay.domain

import android.os.Build
import com.tpaga.productstopay.presentation.products.model.request.PurchaseEntity

object ProductManager {

    private const val DESCRIPTION = "Descripción de la compra"
    private const val EXPIRES_AT = "2019-11-05T20:10:57.549653+00:00"
    private const val USER_IP_ADDRESS = "10.14.2.162"


    private var products = listOf(
        Product("1", "nameuno", "1000"),
        Product("2", "name dos", "2000"),
        Product("3", "name tres", "3000"),
        Product("4", "name cuatro", "4000")
    )

    fun getProducts(): List<Product> {
        return products
    }

    fun getProductToPay(product: Product): PurchaseEntity {
        return PurchaseEntity(
            product.value,
            EXPIRES_AT,
            "${System.currentTimeMillis()}",
            product.id,
            DESCRIPTION,
            "https://www.ptpay.com/product/${product.id}",
            listOf(product),
            Build.ID,
            USER_IP_ADDRESS,
            null
        )
    }

}


