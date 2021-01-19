package com.acquiresdk.activities.ui.faq

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acquiresdk.activities.data.model.GettingStarted
import kotlin.collections.ArrayList

class GettingStartedViewModel : ViewModel() {
    private var dataList: MutableLiveData<List<GettingStarted>>? = null

    internal fun getData(): MutableLiveData<List<GettingStarted>> {
        if (dataList == null) {
            dataList = MutableLiveData()
            loadData()
        }
        return dataList as MutableLiveData<List<GettingStarted>>
    }

    private fun loadData() {
        // do async operation to fetch data
        val dataStringList = ArrayList<GettingStarted>()
        dataStringList.add(
            GettingStarted(
                "Admin Tabs",
                "Acquire\\'s customer support software lets you...",
                ""
            )
        )
        dataStringList.add(
            GettingStarted(
                "Create your Acquire Account",
                "Sign up and get started with Acquire in a...", ""
            )
        )
        dataStringList.add(
            GettingStarted(
                "Home - Live Statistics",
                "-",
                ""
            )
        )
        dataStringList.add(
            GettingStarted(
                "Home - Online Visitors",
                "How to read and navigate the Acquire Home...",
                ""
            )
        )
        dataStringList.add(
            GettingStarted(
                "Home - Try your widget",
                "-",
                ""
            )
        )
        dataStringList.add(
            GettingStarted(
                "Import customer",
                "Directly Import your customer details in Ac...",
                ""
            )
        )

        dataList!!.postValue(dataStringList)
    }
}