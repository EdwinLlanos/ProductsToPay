package com.tpaga.productstopay.respository.remote

import com.tpaga.productstopay.presentation.products.model.request.OrderRequest
import com.tpaga.productstopay.presentation.products.model.response.OrderEntity
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductsApi {
    @POST("payment_requests/create")
    fun buyProduct(@Body orderEntity: OrderRequest): Single<OrderEntity>

    @GET("payment_requests/{token}/info")
    fun getStatus(@Path("token") token: String): Single<OrderEntity>
}