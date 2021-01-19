package com.acquiresdk.activities.ui.faq

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
import com.acquiresdk.activities.data.model.GettingStarted
import com.acquiresdk.adapter.GettingStartedAdapter
import com.acquiresdk.utils.ToolbarUtils
import kotlinx.android.synthetic.main.activity_getting_started.*


class GettingStartedActivity : AppCompatActivity() {
    var data = MutableLiveData<List<GettingStarted>>()
    lateinit var da: List<GettingStarted>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor(Color.WHITE, StatusIconColorType.Dark)
        setContentView(R.layout.activity_getting_started)

        val toolbarInstance = ToolbarUtils()
        toolbarInstance.setToolbar(
            this,
            getString(R.string.title_getting),
            enableBack = true,
            enableSearch = true,
            enableVideo = false,
            enableAudio = false
        )
        recyclerViewStarted.layoutManager = LinearLayoutManager(this)
        val model = ViewModelProvider(this).get(GettingStartedViewModel::class.java)
        model.getData().observe(this, Observer<List<GettingStarted>> { dataList ->
            // update UI
            data.value = dataList
            da = dataList!!
            recyclerViewStarted.adapter = GettingStartedAdapter(this, data)
        })
    }
}