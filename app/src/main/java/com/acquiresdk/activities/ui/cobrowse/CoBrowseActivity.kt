package com.acquiresdk.activities.ui.cobrowse

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.acquire.sdk.utils.StatusIconColorType
import com.acquire.sdk.utils.setStatusBarColor
import com.acquiresdk.R
import com.acquiresdk.utils.ToolbarUtils
import kotlinx.android.synthetic.main.activity_co_browse.*

class CoBrowseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor(Color.WHITE, StatusIconColorType.Dark)
        setContentView(R.layout.activity_co_browse)
        val imageColor = ContextCompat.getColor(this, R.color.colorGray)

        val toolbarInstance = ToolbarUtils()
        toolbarInstance.setToolbar(
            this,
            getString(R.string.title_cobrowse_screen),
            enableBack = true,
            enableSearch = false,
            enableVideo = false,
            enableAudio = false
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val colorStateList = ColorStateList(
                arrayOf(intArrayOf(android.R.attr.state_checked), intArrayOf()),
                intArrayOf(
                    getColor(R.color.colorPrimary)
                    , getColor(R.color.colorGray)
                )
            )
            cbCoAccept.buttonTintList = colorStateList
        }

        imgCoYear.setColorFilter(imageColor, PorterDuff.Mode.SRC_ATOP)
        imgCoMonth.setColorFilter(imageColor, PorterDuff.Mode.SRC_ATOP)
        imgCoCountry.setColorFilter(imageColor, PorterDuff.Mode.SRC_ATOP)
    }
}