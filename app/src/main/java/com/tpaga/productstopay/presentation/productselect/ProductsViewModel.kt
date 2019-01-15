package com.tpaga.productstopay.presentation.productselect

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.tpaga.productstopay.domain.Product
import com.tpaga.productstopay.domain.ProductManager
import com.tpaga.productstopay.presentation.productselect.model.request.PurchaseEntity
import com.tpaga.productstopay.presentation.productselect.model.response.Response
import com.tpaga.productstopay.respository.ProductsRepository
import com.tpaga.productstopay.utilities.Resource
import com.tpaga.productstopay.utilities.setError
import com.tpaga.productstopay.utilities.setLoading
import com.tpaga.productstopay.utilities.setSuccess
import io.reactivex.disposables.CompositeDisposable

class ProductsViewModel(private val productsRepository: ProductsRepository) : ViewModel() {
    private val productList = MutableLiveData<List<Product>>()
    private val compositeDisposable = CompositeDisposable()
    val purchase by lazy { MutableLiveData<Resource<Response>>() }

    val observableProductList: LiveData<List<Product>>
        get() = productList

    init {
        load()
    }

    fun load() {
        productList.value = ProductManager.getProducts()
    }

    fun buyProduct(purchaseEntity: PurchaseEntity) {
        compositeDisposable.add(productsRepository.buyProduct(purchaseEntity)
            .doOnSubscribe { purchase.setLoading() }
            .subscribe({ purchase.setSuccess(it) }, { purchase.setError(it.message) })
        )
    }

}
