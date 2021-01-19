package com.acquiresdk.activities.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acquiresdk.R
import com.acquiresdk.activities.data.model.FAQ
import kotlin.collections.ArrayList

class MainViewModel : ViewModel() {
    private var dataList: MutableLiveData<List<FAQ>>? = null

    internal fun getData(): MutableLiveData<List<FAQ>> {
        if (dataList == null) {
            dataList = MutableLiveData()
            loadData()
        }
        return dataList as MutableLiveData<List<FAQ>>
    }

    private fun loadData() {
        // do async operation to fetch data
        val dataStringList = ArrayList<FAQ>()
        dataStringList.add(
            FAQ(
                "Getting Started",
                R.drawable.ic_in_app_chat,
                "com.acquiresdk.activities.ui.chat.RecentChatActivity"
            )
        )
        dataStringList.add(
            FAQ(
                "Setup Acquire",
                R.drawable.ic_in_app_faq,
                ""
            )
        )
        dataStringList.add(
            FAQ(
                "Setup Triggers",
                R.drawable.ic_in_app_kb,
                ""
            )
        )
        dataStringList.add(
            FAQ(
                "Billing & Packages",
                R.drawable.ic_in_app_widget,
                ""
            )
        )
        dataStringList.add(
            FAQ(
                "Getting Started",
                R.drawable.ic_in_app_chat,
                "com.acquiresdk.activities.ui.chat.RecentChatActivity"
            )
        )
        dataStringList.add(
            FAQ(
                "Setup Acquire",
                R.drawable.ic_in_app_faq,
                ""
            )
        )
        dataStringList.add(
            FAQ(
                "Setup Triggers",
                R.drawable.ic_in_app_kb,
                ""
            )
        )
        dataStringList.add(
            FAQ(
                "Billing & Packages",
                R.drawable.ic_in_app_widget,
                ""
            )
        )
        dataStringList.add(
            FAQ(
                "Getting Started",
                R.drawable.ic_in_app_chat,
                "com.acquiresdk.activities.ui.chat.RecentChatActivity"
            )
        )
        dataStringList.add(
            FAQ(
                "Setup Acquire",
                R.drawable.ic_in_app_faq,
                ""
            )
        )
        dataStringList.add(
            FAQ(
                "Setup Triggers",
                R.drawable.ic_in_app_kb,
                ""
            )
        )
        dataStringList.add(
            FAQ(
                "Billing & Packages",
                R.drawable.ic_in_app_widget,
                ""
            )
        )

        dataList!!.postValue(dataStringList)
    }
}