package com.acquiresdk.activities.ui.faq

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acquiresdk.R
import com.acquiresdk.activities.data.model.FAQ
import kotlin.collections.ArrayList

class FAQViewModel : ViewModel() {
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
                R.drawable.ic_faq_start,
                "com.acquiresdk.activities.ui.faq.GettingStartedActivity"
            )
        )
        dataStringList.add(
            FAQ(
                "Setup Acquire",
                R.drawable.ic_acquire,
                ""
            )
        )
        dataStringList.add(
            FAQ(
                "Setup Triggers",
                R.drawable.ic_faq_trigger,
                ""
            )
        )
        dataStringList.add(
            FAQ(
                "Billing & Packages",
                R.drawable.ic_faq_list,
                ""
            )
        )
        dataStringList.add(
            FAQ(
                "Setup Analytics",
                R.drawable.ic_faq_graph,
                ""
            )
        )
        dataStringList.add(
            FAQ(
                "Setup Chatbots",
                R.drawable.ic_faq_chatbot,
                ""
            )
        )
        dataStringList.add(
            FAQ(
                "Manage Profiles",
                R.drawable.ic_faq_profile,
                ""
            )
        )
        dataStringList.add(
            FAQ(
                "Support with Chat",
                R.drawable.ic_in_app_chat,
                ""
            )
        )
        dataStringList.add(
            FAQ(
                "Settings",
                R.drawable.ic_faq_setting,
                ""
            )
        )
        dataStringList.add(
            FAQ(
                "Integration",
                R.drawable.ic_faq_plugin,
                ""
            )
        )
        dataStringList.add(
            FAQ(
                "Knowledge base",
                R.drawable.ic_in_app_kb,
                ""
            )
        )


        dataList!!.postValue(dataStringList)
    }
}