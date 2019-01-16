package com.tpaga.productstopay.presentation.productpurchased

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.tpaga.productstopay.R
import com.tpaga.productstopay.presentation.productpurchased.ProductPurchasedFragmentArgs.fromBundle
import kotlinx.android.synthetic.main.product_purchased_fragment.*

class ProductPurchasedFragment : Fragment() {

    private lateinit var viewModel: ProductPurchasedViewModel

    private val orderId by lazy {
        fromBundle(arguments!!).orderId
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.product_purchased_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProductPurchasedViewModel::class.java)
        currentProductId.text = "Hollaaa $orderId"
    }

}
