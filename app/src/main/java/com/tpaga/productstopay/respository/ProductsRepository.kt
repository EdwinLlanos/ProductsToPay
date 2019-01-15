package com.tpaga.productstopay.respository

import com.tpaga.productstopay.presentation.productlist.model.request.PurchaseEntity
import com.tpaga.productstopay.presentation.productlist.model.response.Response
import com.tpaga.productstopay.respository.remote.ProductsApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductsRepository (private val productsApi: ProductsApi) {
    fun buyProduct(purchaseEntity: PurchaseEntity): Single<Response> {
        return productsApi.buyProduct(purchaseEntity)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
