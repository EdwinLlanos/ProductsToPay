package com.tpaga.productstopay.presentation.orders


import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tpaga.productstopay.R
import com.tpaga.productstopay.presentation.products.model.response.ProductEntity
import com.tpaga.productstopay.utilities.inflate
import kotlinx.android.synthetic.main.item_order_list.view.*


class OrderListAdapter : RecyclerView.Adapter<OrderListAdapter.ProductHolder>() {

    private var productList: List<ProductEntity> = listOf()

    fun add(productList: List<ProductEntity>) {
        this.productList = productList
        notifyDataSetChanged()
    }

    var onProductClicked: ((product: ProductEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val inflatedView = parent.inflate(R.layout.item_order_list, false)
        return ProductHolder(inflatedView, onProductClicked)

    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bindCity(productList[position])

    }

    override fun getItemCount(): Int {
        return productList.size
    }


    class ProductHolder(
        private val view: View,
        private val onProductClicked: ((product: ProductEntity) -> Unit)?
    ) :
        RecyclerView.ViewHolder(view) {
        private lateinit var product: ProductEntity

        init {
            view.setOnClickListener {
                onProductClicked!!.invoke(product)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bindCity(product: ProductEntity) {
            this.product = product
            view.orderId.text = view.context.getString(R.string.order_text_id, product.orderId)
            view.orderStatus.text = view.context.getString(R.string.order_text_status, product.status)
            view.orderDescription.text = product.purchaseDescription
            view.orderPrice.text = view.context.getString(R.string.order_text_price, product.cost)

        }

    }

}
