package com.tpaga.productstopay.presentation.productselect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tpaga.productstopay.R
import com.tpaga.productstopay.domain.Product
import kotlinx.android.synthetic.main.products_fragment.*

class ProductsFragment : Fragment() {

    private lateinit var viewModel: ProductsViewModel
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

        viewModel = ViewModelProviders.of(this).get(ProductsViewModel::class.java)

        viewModel.observableProductList.observe(this, Observer { noNullNotes ->
            noNullNotes?.let { render(it) }
        })
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
