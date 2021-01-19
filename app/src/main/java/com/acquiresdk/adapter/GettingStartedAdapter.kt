package com.acquiresdk.adapter

import android.content.Context
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.acquiresdk.R
import com.acquiresdk.activities.data.model.GettingStarted
import kotlinx.android.synthetic.main.item_getting_started.view.*

class GettingStartedAdapter(val ctx: Context, val data: LiveData<List<GettingStarted>>) :
    RecyclerView.Adapter<ViewHGettingStarted>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHGettingStarted {
        return ViewHGettingStarted(
            ctx,
            LayoutInflater.from(ctx).inflate(R.layout.item_getting_started, p0, false)
        )
    }

    override fun getItemCount(): Int {
        return data.value!!.size
    }

    override fun onBindViewHolder(p0: ViewHGettingStarted, p1: Int) {
        // val model:Model= data.value!!.get(p1)
        //p0.nam.text=model.name
        p0.bindItems(data.value!!?.get(p1))
    }
}

class ViewHGettingStarted(val ctx: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindItems(model: GettingStarted) {
        val txtGettingStarted = itemView.txtStartedTitle
        val txtStartedSubTitle = itemView.txtStartedSubTitle
        val imgGettingStartedIcon = itemView.imgStartedSubTitle

        txtGettingStarted.text = model.title
        txtStartedSubTitle.text = model.subTitle

        imgGettingStartedIcon.setColorFilter(
            ContextCompat.getColor(ctx, R.color.colorGray),
            PorterDuff.Mode.SRC_ATOP
        )

        itemView.setOnClickListener {
//            val next = Class.forName(model.nextScreen)
//            ctx.startActivity(Intent(ctx, next))
        }
    }
}