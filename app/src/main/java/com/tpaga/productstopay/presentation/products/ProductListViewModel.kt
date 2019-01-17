package com.tpaga.productstopay.presentation.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tpaga.productstopay.domain.Product
import com.tpaga.productstopay.domain.ProductManager
import com.tpaga.productstopay.presentation.products.model.response.OrderEntity
import com.tpaga.productstopay.respository.ProductsRepository
import com.tpaga.productstopay.utilities.Resource
import com.tpaga.productstopay.utilities.setError
import com.tpaga.productstopay.utilities.setLoading
import com.tpaga.productstopay.utilities.setSuccess
import io.reactivex.disposables.CompositeDisposable

class ProductListViewModel(private val productsRepository: ProductsRepository) : ViewModel() {
    private val productList = MutableLiveData<List<Product>>()
    private val compositeDisposable = CompositeDisposable()
    val purchase = MutableLiveData<Resource<OrderEntity>>()
    val productsPending = MutableLiveData<Resource<List<OrderEntity>>>()

    val observableProductList: LiveData<List<Product>>
        get() = productList

    init {
        load()
    }

    private fun load() {
        productList.value = ProductManager.getProducts()
    }

    fun buyProduct(orderRequest: com.tpaga.productstopay.presentation.products.model.request.OrderRequest) {
        compositeDisposable.add(productsRepository.buyProduct(orderRequest)
            .doOnSubscribe { purchase.setLoading() }
            .subscribe({ purchase.setSuccess(it) }, { purchase.setError(it.message) })
        )
    }

    fun validateProduct(productId: String) {
        compositeDisposable.add(
            productsRepository.loadById(productId)
                .subscribe({ productsPending.setSuccess(it) }, { productsPending.setError(it.message) })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()

    }

}
