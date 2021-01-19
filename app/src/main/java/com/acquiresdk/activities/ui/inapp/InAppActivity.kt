package com.acquiresdk.activities.ui.inapp

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.acquire.sdk.utils.StatusIconColorType
import com.acquire.sdk.utils.setStatusBarColor
import com.acquiresdk.R
import com.acquiresdk.activities.data.model.InAppData
import com.acquiresdk.adapter.InAppAdapter
import com.acquiresdk.utils.ToolbarUtils

import kotlinx.android.synthetic.main.activity_in_app.*

class InAppActivity : AppCompatActivity() {
    var data = MutableLiveData<List<InAppData>>()
    lateinit var da: List<InAppData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor(Color.WHITE, StatusIconColorType.Dark)
        setContentView(R.layout.activity_in_app)

        val toolbarInstance = ToolbarUtils()
        toolbarInstance.setToolbar(
            this,
            getString(R.string.title_in_app),
            enableBack = true,
            enableSearch = false,
            enableVideo = false,
            enableAudio = false
        )

        recyclerViewInApp.layoutManager = LinearLayoutManager(this)
        val model = ViewModelProvider(this).get(InAppViewModel::class.java)
        model.getData().observe(this, Observer<List<InAppData>> { dataList ->
            // update UI
            data.value = dataList
            da = dataList!!
            recyclerViewInApp.adapter = InAppAdapter(this, data)
        })
    }
}