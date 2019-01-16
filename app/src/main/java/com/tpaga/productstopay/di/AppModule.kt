package com.tpaga.productstopay.di

import com.tpaga.productstopay.BuildConfig
import com.tpaga.productstopay.cache.Cache
import com.tpaga.productstopay.network.createNetworkClient
import com.tpaga.productstopay.presentation.products.ProductListViewModel
import com.tpaga.productstopay.presentation.products.model.response.ProductEntity
import com.tpaga.productstopay.presentation.orders.OrderListViewModel
import com.tpaga.productstopay.respository.ProductsRepository
import com.tpaga.productstopay.respository.remote.ProductsApi
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.Retrofit

val viewModelModule: Module = module {
    viewModel { ProductListViewModel(productsRepository = get()) }
    viewModel { OrderListViewModel(productsRepository = get()) }
}

val repositoryModule: Module = module {
    single { ProductsRepository(productsApi = get(), cache = get(PURCHASE_ENTITY_CACHE)) }
}

val networkModule: Module = module {
    single { productsApi }
}

val cacheModule: Module = module {
    single(name = PURCHASE_ENTITY_CACHE) { Cache<List<ProductEntity>>() }
}


private const val BASE_URL = "https://stag.wallet.tpaga.co/merchants/api/v1/"

private val retrofit: Retrofit = createNetworkClient(BASE_URL, BuildConfig.DEBUG)


private val productsApi: ProductsApi = retrofit.create(ProductsApi::class.java)

private const val PURCHASE_ENTITY_CACHE = "PURCHASE_ENTITY_CACHE"