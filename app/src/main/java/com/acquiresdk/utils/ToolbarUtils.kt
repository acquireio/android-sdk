package com.acquiresdk.utils

import android.app.Activity
import android.graphics.PorterDuff
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.acquiresdk.R

class ToolbarUtils {
    var instance: ToolbarUtils? = null

    fun ToolbarUtils() {
        instance = this
    }

    fun setToolbar(
        activity: Activity,
        rootView: View,
        title: String,
        enableBack: Boolean,
        enableSearch: Boolean,
        enableVideo: Boolean,
        enableAudio: Boolean
    ) {
        val imgBack = rootView.findViewById(R.id.imgBack) as ImageView
        val imgSearch = rootView.findViewById(R.id.imgSearch) as ImageView
        val imgVideo = rootView.findViewById(R.id.imgVideo) as ImageView
        val imgCall = rootView.findViewById(R.id.imgCall) as ImageView
        val txtTitle = rootView.findViewById(R.id.txtTitle) as TextView

        setToolbarView(
            activity,
            title,
            enableBack,
            enableSearch,
            enableVideo,
            enableAudio,
            imgBack,
            imgSearch,
            imgVideo,
            imgCall,
            txtTitle
        )
    }

    fun setToolbar(
        activity: Activity,
        title: String,
        enableBack: Boolean,
        enableSearch: Boolean,
        enableVideo: Boolean,
        enableAudio: Boolean
    ) {
        val imgBack = activity.findViewById(R.id.imgBack) as ImageView
        val imgSearch = activity.findViewById(R.id.imgSearch) as ImageView
        val imgVideo = activity.findViewById(R.id.imgVideo) as ImageView
        val imgCall = activity.findViewById(R.id.imgCall) as ImageView
        val txtTitle = activity.findViewById(R.id.txtTitle) as TextView

        setToolbarView(
            activity,
            title,
            enableBack,
            enableSearch,
            enableVideo,
            enableAudio,
            imgBack,
            imgSearch,
            imgVideo,
            imgCall,
            txtTitle
        )
    }

    fun setToolbarView(
        activity: Activity,
        title: String,
        enableBack: Boolean,
        enableSearch: Boolean,
        enableVideo: Boolean,
        enableAudio: Boolean,
        imgBack: ImageView,
        imgSearch: ImageView,
        imgVideo: ImageView,
        imgCall: ImageView,
        txtTitle: TextView
    ) {
        if (enableBack) {
            imgBack.visibility = View.VISIBLE
            imgBack.setOnClickListener { activity.finish() }
            imgBack.setColorFilter(
                ContextCompat.getColor(activity, R.color.colorGray),
                PorterDuff.Mode.SRC_ATOP
            )
        } else imgBack.visibility = View.GONE

        txtTitle.setText(title)
        if (enableSearch) imgSearch.visibility = if (enableSearch) View.VISIBLE else View.GONE

        if (enableAudio) {
            imgCall.visibility = View.VISIBLE
        } else imgCall.visibility = View.GONE

        if (enableVideo) {
            imgVideo.visibility = View.VISIBLE
        } else imgVideo.visibility = View.GONE
    }

}