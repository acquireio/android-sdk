package com.acquiresdk

import android.app.Application
import android.content.Context
import android.util.Log
import com.acquire.sdk.AcquireApp


class AppApplication : Application() {
    companion object {
        lateinit var application: Application
        val AcquirePref = "Acquire_sdk"
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        Log.e("Acquire_sdk", "++++++++++++++++ AppApplication onCreate() ++++++++++")
        val prefs =
            getSharedPreferences(AcquirePref, Context.MODE_PRIVATE)
        val accountID = prefs.getString("acc_id", null)
        if (!accountID.isNullOrEmpty()) {
            AcquireApp.init(this, accountID)
        }
    }
}