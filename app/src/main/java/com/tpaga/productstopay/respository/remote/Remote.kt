package com.tpaga.productstopay.respository.remote


import com.tpaga.productstopay.presentation.productselect.model.request.PurchaseEntity
import com.tpaga.productstopay.presentation.productselect.model.response.Response
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface ProductsApi {
    @POST("payment_requests/create")
    fun buyProduct(@Body purchaseEntity: PurchaseEntity): Single<Response>
}