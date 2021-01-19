package com.acquiresdk.adapter

import android.content.Context
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.acquire.sdk.AcquireApp
import com.acquiresdk.R
import com.acquiresdk.activities.data.model.InAppData
import kotlinx.android.synthetic.main.item_in_app.view.*

class InAppAdapter(val ctx: Context, val data: LiveData<List<InAppData>>) :
    RecyclerView.Adapter<ViewH>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewH {
        return ViewH(ctx, LayoutInflater.from(ctx).inflate(R.layout.item_in_app, p0, false))
    }

    override fun getItemCount(): Int {
        return data.value!!.size
    }

    override fun onBindViewHolder(p0: ViewH, p1: Int) {
        // val model:Model= data.value!!.get(p1)
        //p0.nam.text=model.name
        p0.bindItems(data.value!!?.get(p1))
    }
}

class ViewH(val ctx: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindItems(model: InAppData) {
        val title = itemView.txtTitle
        val subTitle = itemView.txtSubTitle
        val imgInApp = itemView.imgInApp

        title.text = model.title
        subTitle.text = model.subTitle
        imgInApp.setImageResource(model.icon)

        val imgNext = itemView.imgNext
        imgNext.setColorFilter(
            ContextCompat.getColor(ctx, R.color.colorGray),
            PorterDuff.Mode.SRC_ATOP
        )

        itemView.setOnClickListener {
            if (adapterPosition == 0) AcquireApp.startSupportChat()
        }
    }
}