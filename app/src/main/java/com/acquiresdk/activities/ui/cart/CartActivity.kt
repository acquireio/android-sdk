package com.acquiresdk.activities.ui.cart

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.acquire.sdk.AcquireApp
import com.acquire.sdk.utils.StatusIconColorType
import com.acquire.sdk.utils.setStatusBarColor
import com.acquiresdk.R
import com.acquiresdk.activities.data.model.Cart
import com.acquiresdk.adapter.CartProductAdapter
import com.acquiresdk.utils.ToolbarUtils
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : AppCompatActivity() {
    var data = MutableLiveData<List<Cart>>()
    lateinit var da: List<Cart>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor(Color.WHITE, StatusIconColorType.Dark)
        setContentView(R.layout.activity_cart)

        val toolbarInstance = ToolbarUtils()
        toolbarInstance.setToolbar(
            this,
            getString(R.string.title_cart),
            enableBack = true,
            enableSearch = false,
            enableVideo = false,
            enableAudio = false
        )

        recyclerViewCart.layoutManager = LinearLayoutManager(this)
        val model = ViewModelProvider(this).get(CartViewModel::class.java)
        model.getData().observe(this, Observer<List<Cart>> { dataList ->
            // update UI
            data.value = dataList
            da = dataList!!
            recyclerViewCart.adapter = CartProductAdapter(this, data)
        })
    }

    fun startChat(view: View?) {
        AcquireApp.startSupportChat()
    }
}