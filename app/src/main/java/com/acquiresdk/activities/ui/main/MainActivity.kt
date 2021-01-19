package com.acquiresdk.activities.ui.main

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.acquire.sdk.utils.StatusIconColorType
import com.acquire.sdk.utils.setStatusBarColor
import com.acquiresdk.R
import com.acquiresdk.activities.ui.home.HomeFragment
import com.acquiresdk.activities.ui.more.MoreFragment
import com.acquiresdk.activities.ui.settings.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var mTxtMenuHome: TextView? = null
    private var mTvMenuSettings: TextView? = null
    private var mTvMenuMore: TextView? = null
    private var mImgMenuHome: ImageView? = null
    private var mImgMenuSettings: ImageView? = null
    private var mImgMenuMore: ImageView? = null


    var data = MutableLiveData<List<MainViewModel>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor(Color.WHITE, StatusIconColorType.Dark)
        setContentView(R.layout.activity_main)
        findViews()

        // default set to 0
        setFragment(0, HomeFragment())

        llMenuHome.setOnClickListener {
            setFragment(0, HomeFragment())
        }

        llMenuSettings.setOnClickListener {
            setFragment(1, SettingsFragment())
        }

        llMenuMore.setOnClickListener {
            setFragment(2, MoreFragment())
        }
    }

    private fun findViews() {
        mTxtMenuHome = findViewById(R.id.txtMenuHome)
        mTvMenuSettings = findViewById(R.id.tvMenuSettings)
        mTvMenuMore = findViewById(R.id.tvMenuMore)
        mImgMenuHome = findViewById(R.id.imgMenuHome)
        mImgMenuSettings = findViewById(R.id.imgMenuSettings)
        mImgMenuMore = findViewById(R.id.imgMenuMore)
    }

    fun setFragment(position: Int, fragment: Fragment) {
        setSelectedColor(position)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.commitAllowingStateLoss()
    }

    fun setSelectedColor(position: Int) {
        val selectedColor = ContextCompat.getColor(this, R.color.colorPrimary)
        val normalColor = ContextCompat.getColor(this, R.color.colorGray)

        val isHome = if (position == 0) selectedColor else normalColor
        val isSettings = if (position == 1) selectedColor else normalColor
        val isMore = if (position == 2) selectedColor else normalColor

        mTxtMenuHome?.setTextColor(isHome)
        mTvMenuSettings?.setTextColor(isSettings)
        mTvMenuMore?.setTextColor(isMore)
        mImgMenuHome?.setColorFilter(isHome, PorterDuff.Mode.SRC_ATOP)
        mImgMenuSettings?.setColorFilter(isSettings, PorterDuff.Mode.SRC_ATOP)
        mImgMenuMore?.setColorFilter(isMore, PorterDuff.Mode.SRC_ATOP)
    }

}