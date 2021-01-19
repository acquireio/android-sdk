package com.acquiresdk.activities.ui.more

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acquiresdk.R
import com.acquiresdk.activities.data.model.More

class MoreViewModel : ViewModel() {
    private var dataList: MutableLiveData<List<More>>? = null

    internal fun getData(): MutableLiveData<List<More>> {
        if (dataList == null) {
            dataList = MutableLiveData()
            loadData()
        }
        return dataList as MutableLiveData<List<More>>
    }

    private fun loadData() {
        // do async operation to fetch data
        val dataStringList = ArrayList<More>()
        dataStringList.add(
            More(
                "INIT SDK",
                "A small description about INIT SDK",
                R.drawable.ic_more_reload,
                ""
            )
        )
        dataStringList.add(
            More(
                "Audio test",
                "A quick audio test will start",
                R.drawable.ic_splash_call,
                ""
            )
        )
        dataStringList.add(
            More(
                "Video test",
                "A quick audio test will start",
                R.drawable.ic_splash_video_call,
                ""
            )
        )

        dataList!!.postValue(dataStringList)
    }
}