package com.tpaga.productstopay.presentation.products


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tpaga.productstopay.R
import com.tpaga.productstopay.domain.Product
import com.tpaga.productstopay.utilities.inflate
import kotlinx.android.synthetic.main.item_product_list.view.*


class ProductListAdapter : RecyclerView.Adapter<ProductListAdapter.ProductHolder>() {

    private var productList: List<Product> = listOf()

    fun add(productList: List<Product>) {
        this.productList = productList
        notifyDataSetChanged()
    }

    var onProductClicked: ((product: Product) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val inflatedView = parent.inflate(R.layout.item_product_list, false)
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
        private val onCityClickAction: ((product: Product) -> Unit)?
    ) :
        RecyclerView.ViewHolder(view) {
        private lateinit var product: Product

        init {
            view.setOnClickListener {
                onCityClickAction!!.invoke(product)
            }
        }


        fun bindCity(product: Product) {
            this.product = product
            view.productName.text = product.name
            view.productDescription.text = product.description
            view.productPrice.text = "Valor: $" + product.value
        }

    }

}
