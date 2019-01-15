package com.tpaga.productstopay.di

import com.tpaga.productstopay.BuildConfig
import com.tpaga.productstopay.network.createNetworkClient
import com.tpaga.productstopay.presentation.productselect.ProductsViewModel
import com.tpaga.productstopay.respository.ProductsRepository
import com.tpaga.productstopay.respository.remote.ProductsApi
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.Retrofit

val viewModelModule: Module = module {
    viewModel { ProductsViewModel(productsRepository = get()) }
}

val repositoryModule: Module = module {
    single { ProductsRepository(productsApi = get()) }
}

val networkModule: Module = module {
    single { productsApi }
}


private const val BASE_URL = "http://jsonplaceholder.typicode.com/"

private val retrofit: Retrofit = createNetworkClient(BASE_URL, BuildConfig.DEBUG)


private val productsApi: ProductsApi = retrofit.create(ProductsApi::class.java)