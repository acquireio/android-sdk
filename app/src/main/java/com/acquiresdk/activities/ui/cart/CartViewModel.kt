package com.acquiresdk.activities.ui.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acquiresdk.R
import com.acquiresdk.activities.data.model.Cart
import kotlin.collections.ArrayList

class CartViewModel : ViewModel() {
    private var dataList: MutableLiveData<List<Cart>>? = null

    internal fun getData(): MutableLiveData<List<Cart>> {
        if (dataList == null) {
            dataList = MutableLiveData()
            loadData()
        }
        return dataList as MutableLiveData<List<Cart>>
    }

    private fun loadData() {
        // do async operation to fetch data
        val dataStringList = ArrayList<Cart>()
        dataStringList.add(
            Cart(
                "Nike Air Zoom Pegasus 36 Miami ",
                R.drawable.dummy_cart,
                29943f,
                1,
                true
            )
        )
        dataStringList.add(
            Cart(
                "Nike Air Zoom Pegasus 36 Miami ",
                R.drawable.dummy_cart,
                29943f,
                1,
                true
            )
        )

        dataList!!.postValue(dataStringList)
    }
}