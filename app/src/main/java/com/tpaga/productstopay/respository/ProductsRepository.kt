package com.tpaga.productstopay.respository

import com.tpaga.productstopay.cache.Cache
import com.tpaga.productstopay.presentation.productlist.model.request.PurchaseEntity
import com.tpaga.productstopay.presentation.productlist.model.response.ProductEntity
import com.tpaga.productstopay.respository.remote.ProductsApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductsRepository constructor(
    private val productsApi: ProductsApi,
    private val cache: Cache<List<ProductEntity>>
) {
    private val key = "User Purchases"
    fun buyProduct(purchaseEntity: PurchaseEntity): Single<ProductEntity> {
        return productsApi.buyProduct(purchaseEntity)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { set(it) }
    }

    private fun set(it: ProductEntity?) {
        it?.let {
            cache.save(key, listOf(it))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        }
    }

    fun load(productId: String): Single<List<ProductEntity>> {
        return cache.load(key)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .map { list ->
                list.filter { it.orderId == productId }
            }
    }
}
