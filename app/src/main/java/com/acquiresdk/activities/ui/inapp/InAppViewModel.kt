package com.acquiresdk.activities.ui.inapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acquiresdk.R
import com.acquiresdk.activities.data.model.InAppData
import kotlin.collections.ArrayList

class InAppViewModel : ViewModel() {
    private var dataList: MutableLiveData<List<InAppData>>? = null

    internal fun getData(): MutableLiveData<List<InAppData>> {
        if (dataList == null) {
            dataList = MutableLiveData()
            loadData()
        }
        return dataList as MutableLiveData<List<InAppData>>
    }

    private fun loadData() {
        // do async operation to fetch data
        val dataStringList = ArrayList<InAppData>()
        dataStringList.add(
            InAppData(
                "Recent Conversations",
                "No new open tickets",
                R.drawable.ic_in_app_chat,
                "com.acquire.sdk.activities.RecentChatActivity"
            )
        )
        dataStringList.add(
            InAppData(
                "FAQs",
                "Frequently asked questions",
                R.drawable.ic_in_app_faq,
                "com.acquiresdk.activities.ui.faq.FAQActivity"
            )
        )
        dataStringList.add(
            InAppData(
                "Acquire KB",
                "Explore knowledge base",
                R.drawable.ic_in_app_kb,
                ""
            )
        )
        dataStringList.add(
            InAppData(
                "Widget Apps 1",
                "Start widget apps test",
                R.drawable.ic_in_app_widget,
                ""
            )
        )

        dataList!!.postValue(dataStringList)
    }
}