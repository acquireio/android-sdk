package com.acquiresdk.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.acquiresdk.R
import com.acquiresdk.activities.data.model.Cart
import kotlinx.android.synthetic.main.item_cart_product.view.*

class CartProductAdapter(val ctx: Context, val data: LiveData<List<Cart>>) :
    RecyclerView.Adapter<ViewHCart>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHCart {
        return ViewHCart(
            ctx,
            LayoutInflater.from(ctx).inflate(R.layout.item_cart_product, p0, false)
        )
    }

    override fun getItemCount(): Int {
        return data.value!!.size
    }

    override fun onBindViewHolder(p0: ViewHCart, p1: Int) {
        // val model:Model= data.value!!.get(p1)
        //p0.nam.text=model.name
        p0.bindItems(data.value!!.get(p1))
    }
}

class ViewHCart(val ctx: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindItems(model: Cart) {
        val txtProductName = itemView.txtProductName
        val imgProduct = itemView.imgProduct
        val imgPWishList = itemView.imgPWishList
        val imgPDelete = itemView.imgPDelete
        val txtProductPrice = itemView.txtProductPrice
        val txtProductQty = itemView.txtProductQty

        txtProductName.text = model.name
        txtProductPrice.text = String.format("$%s", model.price)
        txtProductQty.text = String.format("%s", model.qty)

        imgPWishList.setImageResource(if (model.isWishList) R.drawable.ic_wishlist_fill else R.drawable.ic_wishlist)

    }
}