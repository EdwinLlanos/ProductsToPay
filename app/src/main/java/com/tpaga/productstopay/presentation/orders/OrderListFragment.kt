package com.tpaga.productstopay.presentation.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tpaga.productstopay.R
import com.tpaga.productstopay.presentation.orders.OrderListFragmentArgs.fromBundle
import com.tpaga.productstopay.presentation.products.model.response.ProductEntity
import com.tpaga.productstopay.utilities.Resource
import com.tpaga.productstopay.utilities.ResourceState
import com.tpaga.productstopay.utilities.gone
import com.tpaga.productstopay.utilities.visible
import kotlinx.android.synthetic.main.loading_view.*
import kotlinx.android.synthetic.main.product_purchased_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class OrderListFragment : Fragment() {

    private val viewModel: OrderListViewModel by viewModel()
    private var adapter = OrderListAdapter()

    private val orderId by lazy {
        fromBundle(arguments!!).orderId
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.orderList.observe(this, Observer { render(it) })
        viewModel.orderUpdate.observe(this, Observer { orderUpdate(it) })
        validateOrderId()
    }

    private fun validateOrderId() {
        if (orderId != "0")
            viewModel.getTokenByOrderId(orderId)
    }

    private fun orderUpdate(it: Resource<ProductEntity>?) {

        when (it?.state) {
            ResourceState.LOADING -> loading.visible()
            ResourceState.SUCCESS -> {
                loading.gone()
                showAlertDialog(it)
            }
            ResourceState.ERROR -> loading.gone()
        }
    }

    private fun showAlertDialog(it: Resource<ProductEntity>?) {
        it?.data?.let { noNullOrder ->
            val builder = AlertDialog.Builder(context!!)
            builder.setTitle(getString(R.string.order_update))
            builder.setMessage(getString(R.string.message_order_update, noNullOrder.orderId, noNullOrder.status))
            builder.setPositiveButton(getString(R.string.text_button_accept)) { dialog, _ ->
                dialog.cancel()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }

    private fun render(it: Resource<List<ProductEntity>>?) {
        var ordersCount = 0
        it?.data?.let {
            ordersCount = it.size
            adapter.add(it)
        }
        setTitle(ordersCount)
    }

    private fun setTitle(ordersCount: Int) {
        title.text = getString(R.string.title_product_purchased, ordersCount)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.product_purchased_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()

    }

    private fun initRecyclerView() {
        ordersRecyclerView.layoutManager = LinearLayoutManager(context)
        ordersRecyclerView.adapter = adapter
        ordersRecyclerView.setHasFixedSize(true)
        adapter.apply {
            onProductClicked = this@OrderListFragment::orderClicked
        }
    }

    private fun orderClicked(product: ProductEntity) {
        viewModel.getStatus(product.token)
    }

}
