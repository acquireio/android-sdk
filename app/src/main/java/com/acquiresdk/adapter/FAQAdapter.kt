package com.acquiresdk.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.acquiresdk.R
import com.acquiresdk.activities.data.model.FAQ
import kotlinx.android.synthetic.main.item_faq1.view.*

class FAQAdapter(val ctx: Context, val data: LiveData<List<FAQ>>) :
    RecyclerView.Adapter<ViewHFAQ>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHFAQ {
        return ViewHFAQ(ctx, LayoutInflater.from(ctx).inflate(R.layout.item_faq1, p0, false))
    }

    override fun getItemCount(): Int {
        return data.value!!.size
    }

    override fun onBindViewHolder(p0: ViewHFAQ, p1: Int) {
        // val model:Model= data.value!!.get(p1)
        //p0.nam.text=model.name
        p0.bindItems(data.value!![p1])
    }
}

class ViewHFAQ(val ctx: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindItems(model: FAQ) {
        val txtFAQ = itemView.txtFAQ
        val imgFAQIcon = itemView.imgFAQIcon

        txtFAQ.text = model.title
        imgFAQIcon.setImageResource(model.icon)

        itemView.setOnClickListener {
            val next = Class.forName(model.nextScreen)
            ctx.startActivity(Intent(ctx, next))
        }
    }
}