package com.tpaga.productstopay.respository

import com.tpaga.productstopay.presentation.productselect.model.response.Response
import com.tpaga.productstopay.respository.remote.ProductsApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductsRepository (private val productsApi: ProductsApi) {
    fun get(id:Int): Single<Response> {
        return productsApi.get(id)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
