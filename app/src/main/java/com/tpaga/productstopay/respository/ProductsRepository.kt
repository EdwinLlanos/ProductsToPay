package com.tpaga.productstopay.respository

import com.tpaga.productstopay.cache.Cache
import com.tpaga.productstopay.presentation.products.model.request.OrderRequest
import com.tpaga.productstopay.presentation.products.model.response.OrderEntity
import com.tpaga.productstopay.respository.remote.ProductsApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductsRepository constructor(
    private val productsApi: ProductsApi,
    private val cache: Cache<List<OrderEntity>>
) {
    private val key = "User Purchases"
    fun buyProduct(orderRequest: OrderRequest): Single<OrderEntity> {
        return productsApi.buyProduct(orderRequest)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { set(it) }
    }

    fun getStatus(token: String): Single<OrderEntity> {
        return productsApi.getStatus(token)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { set(it) }
    }

    private fun set(order: OrderEntity?) {
        val newList: MutableList<OrderEntity> = ArrayList()
        order?.let {
            cache.load(key)
                .subscribe({ list ->
                    newList.addAll(list.filter { p -> p.orderId != order.orderId })
                    newList.add(order)
                    saveList(newList)
                }, {
                    saveList(newList)
                    set(order)
                })
        }
    }

    private fun saveList(list: MutableList<OrderEntity>) {
        cache.save(key, list)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun loadById(productId: String): Single<List<OrderEntity>> {
        return cache.load(key)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .map { list ->
                list.filter { it.orderId == productId }
            }
    }

    fun loadAll(): Single<List<OrderEntity>> {
        return cache.load(key)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())

    }
}
