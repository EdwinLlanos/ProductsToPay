package com.tpaga.productstopay.domain

import android.os.Build
import com.tpaga.productstopay.presentation.products.model.request.PurchaseEntity

object ProductManager {

    private const val DESCRIPTION = "Lorem ipsum dolor sit amet, consectetur adipisicing elit."
    private const val EXPIRES_AT = "2019-11-05T20:10:57.549653+00:00"
    private const val USER_IP_ADDRESS = "10.14.2.162"


    private var products = listOf(
        Product(
            "1",
            "Café late",
            "1000",
            "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Adipisci aut exercitationem nesciunt nulla quam reiciendis"
        ),
        Product(
            "2",
            "Capuchino",
            "2000",
            "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Adipisci aut exercitationem nesciunt nulla quam reiciendis"
        ),
        Product(
            "3",
            "Café expreso",
            "3000",
            "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Adipisci aut exercitationem nesciunt nulla quam reiciendis"
        ),
        Product(
            "4",
            "Chocolate",
            "4000",
            "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Adipisci aut exercitationem nesciunt nulla quam reiciendis"
        ),
        Product(
            "4",
            "Mocaccino",
            "4500",
            "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Adipisci aut exercitationem nesciunt nulla quam reiciendis"
        )
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


