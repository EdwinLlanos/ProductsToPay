package com.tpaga.productstopay.network

import com.tpaga.productstopay.network.CONST.AUTHORIZATION
import com.tpaga.productstopay.network.CONST.TOKEN
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

fun createNetworkClient(baseUrl: String, debug: Boolean = false) =
    retrofitClient(baseUrl, httpClient(debug))

private fun httpClient(debug: Boolean): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
    val clientBuilder = OkHttpClient.Builder()
    if (debug) {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(httpLoggingInterceptor)
    }
    clientBuilder.addInterceptor(requestInterceptor)
    return clientBuilder.build()
}

private fun retrofitClient(baseUrl: String, httpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()


private var requestInterceptor = Interceptor { chain ->
    val request = chain.request()
        .newBuilder()
        .addHeader(AUTHORIZATION, TOKEN)
        .build()
    chain.proceed(request)
}

object CONST {
    const val AUTHORIZATION = "Authorization"
    const val TOKEN = "Basic bWluaWFwcC1nYXRvMjptaW5pYXBwbWEtMTIz"

}