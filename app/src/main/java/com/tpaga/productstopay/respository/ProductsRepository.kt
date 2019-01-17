package com.tpaga.productstopay.respository

import com.tpaga.productstopay.cache.Cache
import com.tpaga.productstopay.presentation.products.model.request.PurchaseEntity
import com.tpaga.productstopay.presentation.products.model.response.ProductEntity
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

    fun getStatus(token: String): Single<ProductEntity> {
        return productsApi.getStatus(token)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { set(it) }
    }

    private fun set(product: ProductEntity?) {
        val newList: MutableList<ProductEntity> = ArrayList()
        product?.let {
            cache.load(key)
                .subscribe({ list ->
                    newList.addAll(list.filter { p -> p.orderId != product.orderId })
                    newList.add(product)
                    saveList(newList)
                }, {
                    saveList(newList)
                    set(product)
                })
        }
    }

    private fun saveList(list: MutableList<ProductEntity>) {
        cache.save(key, list)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun loadById(productId: String): Single<List<ProductEntity>> {
        return cache.load(key)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .map { list ->
                list.filter { it.orderId == productId }
            }
    }

    fun loadAll(): Single<List<ProductEntity>> {
        return cache.load(key)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())

    }
}
