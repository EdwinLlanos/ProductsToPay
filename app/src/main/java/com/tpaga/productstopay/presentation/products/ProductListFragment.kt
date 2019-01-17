package com.tpaga.productstopay.presentation.products

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tpaga.productstopay.R
import com.tpaga.productstopay.domain.Product
import com.tpaga.productstopay.domain.ProductManager.getProductToPay
import com.tpaga.productstopay.presentation.products.model.response.ProductEntity
import com.tpaga.productstopay.utilities.Resource
import com.tpaga.productstopay.utilities.ResourceState
import com.tpaga.productstopay.utilities.gone
import com.tpaga.productstopay.utilities.visible
import kotlinx.android.synthetic.main.loading_view.*
import kotlinx.android.synthetic.main.products_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProductListFragment : Fragment() {

    private val viewModel: ProductListViewModel by viewModel()

    private var adapter = ProductListAdapter()

    private lateinit var product: Product

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.products_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.observableProductList.observe(this, Observer { noNullNotes ->
            noNullNotes?.let { render(it) }
        })

        viewModel.purchase.observe(this, Observer { noNullStore ->
            noNullStore?.let { render(it) }
        })

        viewModel.productsPending.observe(this, Observer { renderList(it) })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
    }

    private fun renderList(it: Resource<List<ProductEntity>>?) {
        if (it?.data?.size == 1) {
            showAlertDialog()
            return
        }
        viewModel.buyProduct(
            getProductToPay(product)
        )
    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(context!!)
        builder.setTitle(getString(R.string.order_notice))
        builder.setMessage(getString(R.string.message_confirm_purchase))
        builder.setPositiveButton(getString(R.string.text_button_accept)) { dialog, _ ->
            dialog.cancel()
            viewModel.buyProduct(
                getProductToPay(product)
            )
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }


    private fun render(it: Resource<ProductEntity>) {
        when (it.state) {
            ResourceState.LOADING -> loading.visible()
            ResourceState.SUCCESS -> {
                loading.gone()
                it.data?.run {
                    val myUri = Uri.parse(tpagaPaymentUrl)
                    val browserIntent = Intent(Intent.ACTION_VIEW, myUri)
                    startActivity(browserIntent)
                }
            }
            ResourceState.ERROR -> loading.gone()
        }
        it.message?.run {
            Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
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
            onProductClicked = this@ProductListFragment::buyProduct
        }
    }

    private fun buyProduct(product: Product) {
        this.product = product
        viewModel.validateProduct(product.id)
    }
}

