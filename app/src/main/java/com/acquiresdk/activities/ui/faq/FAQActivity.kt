package com.acquiresdk.activities.ui.faq

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.acquire.sdk.utils.StatusIconColorType
import com.acquire.sdk.utils.setStatusBarColor
import com.acquiresdk.R
import com.acquiresdk.activities.data.model.FAQ
import com.acquiresdk.adapter.FAQAdapter
import com.acquiresdk.utils.ToolbarUtils
import kotlinx.android.synthetic.main.activity_faq.*


class FAQActivity : AppCompatActivity() {
    var data = MutableLiveData<List<FAQ>>()
    lateinit var da: List<FAQ>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor(Color.WHITE, StatusIconColorType.Dark)
        setContentView(R.layout.activity_faq)

        val toolbarInstance = ToolbarUtils()
        toolbarInstance.setToolbar(
            this,
            getString(R.string.title_faqs),
            enableBack = true,
            enableSearch = false,
            enableVideo = false,
            enableAudio = false
        )

        recyclerViewFAQ.layoutManager = GridLayoutManager(this,4)
        val model = ViewModelProvider(this).get(FAQViewModel::class.java)
        model.getData().observe(this, Observer<List<FAQ>> { dataList ->
            // update UI
            data.value = dataList
            da = dataList!!
            recyclerViewFAQ.adapter = FAQAdapter(this, data)
        })
    }
}