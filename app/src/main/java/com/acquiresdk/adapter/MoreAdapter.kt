package com.acquiresdk.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.acquiresdk.R
import com.acquiresdk.activities.data.model.More
import kotlinx.android.synthetic.main.item_in_app.view.*

class MoreAdapter(val ctx: Context, val data: LiveData<List<More>>) :
    RecyclerView.Adapter<ViewHMore>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHMore {
        return ViewHMore(ctx, LayoutInflater.from(ctx).inflate(R.layout.item_in_app, p0, false))
    }

    override fun getItemCount(): Int {
        return data.value!!.size
    }

    override fun onBindViewHolder(p0: ViewHMore, p1: Int) {
        // val model:Model= data.value!!.get(p1)
        //p0.nam.text=model.name
        p0.bindItems(data.value!!?.get(p1))
    }
}

class ViewHMore(val ctx: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindItems(model: More) {
        val title = itemView.txtTitle
        val subTitle = itemView.txtSubTitle
        val imgInApp = itemView.imgInApp

        title.text = model.title
        subTitle.text = model.subTitle
        imgInApp.setImageResource(model.icon)
        imgInApp.setColorFilter(
            Color.parseColor("#14359A"),
            PorterDuff.Mode.SRC_ATOP
        )
        val imgNext = itemView.imgNext
        imgNext.setColorFilter(
            ContextCompat.getColor(ctx, R.color.colorGray),
            PorterDuff.Mode.SRC_ATOP
        )

        itemView.setOnClickListener {
//            val next = Class.forName(model.nextScreen)
//            ctx.startActivity(Intent(ctx, next))
        }
    }
}