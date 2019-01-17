package com.tpaga.productstopay.respository.remote


import com.tpaga.productstopay.presentation.products.model.request.PurchaseEntity
import com.tpaga.productstopay.presentation.products.model.response.ProductEntity
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductsApi {
    @POST("payment_requests/create")
    fun buyProduct(@Body purchaseEntity: PurchaseEntity): Single<ProductEntity>

    @GET("payment_requests/{token}/info")
    fun getStatus(@Path("token") token: String): Single<ProductEntity>
}