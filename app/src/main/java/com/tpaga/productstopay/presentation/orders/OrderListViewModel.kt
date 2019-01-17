package com.tpaga.productstopay.presentation.orders

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tpaga.productstopay.presentation.products.model.response.OrderEntity
import com.tpaga.productstopay.respository.ProductsRepository
import com.tpaga.productstopay.utilities.Resource
import com.tpaga.productstopay.utilities.setError
import com.tpaga.productstopay.utilities.setLoading
import com.tpaga.productstopay.utilities.setSuccess
import io.reactivex.disposables.CompositeDisposable

class OrderListViewModel(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val orderList = MutableLiveData<Resource<List<OrderEntity>>>()
    val orderUpdate = MutableLiveData<Resource<OrderEntity>>()

    init {
        load()
    }

    fun load() {
        compositeDisposable.add(
            productsRepository.loadAll()
                .doOnSubscribe { orderList.setLoading() }
                .subscribe({ orderList.setSuccess(it) }, { orderList.setError(it.message) })
        )
    }

    fun getStatus(token: String) {
        compositeDisposable.add(
            productsRepository.getStatus(token)
                .doOnSubscribe { orderUpdate.setLoading() }
                .subscribe(
                    { orderUpdate.setSuccess(it) }, { orderUpdate.setError(it.message) })
        )
    }

    fun getTokenByOrderId(orderId: String) {
        compositeDisposable.add(
            productsRepository.loadById(orderId)
                .subscribe({ getStatus(it[0].token) }, {})
        )
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


}
