package com.tpaga.productstopay.presentation.orders


import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tpaga.productstopay.R
import com.tpaga.productstopay.presentation.products.model.response.OrderEntity
import com.tpaga.productstopay.utilities.inflate
import kotlinx.android.synthetic.main.item_order_list.view.*


class OrderListAdapter : RecyclerView.Adapter<OrderListAdapter.OrderHolder>() {

    private var orderList: List<OrderEntity> = listOf()

    fun add(orderList: List<OrderEntity>) {
        this.orderList = orderList
        notifyDataSetChanged()
    }

    var onProductClicked: ((order: OrderEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        val inflatedView = parent.inflate(R.layout.item_order_list, false)
        return OrderHolder(inflatedView, onProductClicked)

    }

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        holder.OrderCity(orderList[position])

    }

    override fun getItemCount(): Int {
        return orderList.size
    }


    class OrderHolder(
        private val view: View,
        private val onProductClicked: ((order: OrderEntity) -> Unit)?
    ) :
        RecyclerView.ViewHolder(view) {
        private lateinit var order: OrderEntity

        init {
            view.setOnClickListener {
                onProductClicked!!.invoke(order)
            }
        }

        @SuppressLint("SetTextI18n")
        fun OrderCity(order: OrderEntity) {
            this.order = order
            view.orderId.text = view.context.getString(R.string.order_text_id, order.orderId)
            view.orderStatus.text = view.context.getString(R.string.order_text_status, order.status)
            view.orderDescription.text = order.purchaseDescription
            view.orderPrice.text = view.context.getString(R.string.order_text_price, order.cost)
            view.productName.text = view.context.getString(R.string.order_text_name, order.purchaseItems[0].name)
            view.productCount.text = view.context.getString(R.string.order_text_count, order.purchaseItems.size)
        }

    }

}
