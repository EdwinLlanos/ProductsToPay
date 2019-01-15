package com.tpaga.productstopay.presentation.productselect

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.tpaga.productstopay.domain.Product
import com.tpaga.productstopay.domain.ProductsManager
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
    val store by lazy { MutableLiveData<Resource<Response>>() }

    val observableProductList: LiveData<List<Product>>
        get() = productList

    init {
        load()
    }

    fun load() {
        productList.value = ProductsManager.getProducts()
    }

    fun getInfo() {
        compositeDisposable.add(productsRepository.get(23)
            .doOnSubscribe { store.setLoading() }
            .subscribe({ store.setSuccess(it) }, { store.setError(it.message) }))
    }

}
