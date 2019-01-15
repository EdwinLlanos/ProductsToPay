package com.tpaga.productstopay.presentation.productselect

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.tpaga.productstopay.domain.Product
import com.tpaga.productstopay.domain.ProductsManager

class ProductsViewModel : ViewModel() {
    private val productList = MutableLiveData<List<Product>>()

    val observableProductList: LiveData<List<Product>>
        get() = productList

    init {
        load()
    }

    private fun load() {
        productList.value = ProductsManager.getProducts()
    }
}
