package com.tpaga.productstopay.presentation.productselect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tpaga.productstopay.R
import com.tpaga.productstopay.domain.Product
import com.tpaga.productstopay.presentation.productselect.model.response.Response
import com.tpaga.productstopay.utilities.Resource
import com.tpaga.productstopay.utilities.ResourceState
import com.tpaga.productstopay.utilities.gone
import com.tpaga.productstopay.utilities.visible
import kotlinx.android.synthetic.main.loading_view.*
import kotlinx.android.synthetic.main.products_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProductsFragment : Fragment() {

    private val viewModel: ProductsViewModel by viewModel()

    private var adapter = ProductListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.products_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()

        viewModel.observableProductList.observe(this, Observer { noNullNotes ->
            noNullNotes?.let { render(it) }
        })

        viewModel.store.observe(this, Observer { noNullStore ->
            noNullStore?.let { render(it) }
        })
    }

    private fun render(it: Resource<Response>) {
        when (it.state) {
            ResourceState.LOADING -> loading.visible()
            ResourceState.SUCCESS -> loading.gone()
            ResourceState.ERROR -> loading.gone()
        }
    }

    private fun render(it: List<Product>) {
        adapter.add(it)

    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        adapter.apply {
            onProductClicked = this@ProductsFragment::buyProduct
        }
    }

    private fun buyProduct(product: Product) {
        Toast.makeText(context, product.name, Toast.LENGTH_SHORT).show()
    }
}
