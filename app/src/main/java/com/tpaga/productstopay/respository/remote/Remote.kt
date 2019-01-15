package com.tpaga.productstopay.respository.remote


import com.tpaga.productstopay.presentation.productselect.model.response.Response
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductsApi {

    @POST("init")
    fun get(@Query("user_id") userId: Int): Single<Response>

}