package com.acquiresdk

import android.app.Application
import com.acquire.sdk.AcquireApp


class AppApplication : Application() {
    companion object {
        lateinit var application: Application
        val AcquirePref = "Acquire_sdk"
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        AcquireApp.registerApp(this)
//        Log.e("Acquire_sdk", "++++++++++++++++ AppApplication onCreate() ++++++++++")
        /*  val jsonObj = JSONObject()
          jsonObj.put("email_extra","your@xtra.email.com")
          jsonObj.put("phone","+91-9924132080")
          jsonObj.put("email","primary@mail.com")
          AcquireApp.setVisitorDetail(jsonObj)*/

//        val prefs =
//            getSharedPreferences(AcquirePref, Context.MODE_PRIVATE)
//        val accountID = prefs.getString("acc_id", null)
//        AcquireApp.setVisitorHash("aee879dc5aa948392d6e8189be54e44d1e1a820b0a3c45801f410ed4996cec68")
//        AcquireApp.setVisitorDetail(JSONObject().put("email", "nilay@acquire.io"))
//        if (!accountID.isNullOrEmpty()) {
//            AcquireApp.init(this, accountID)
//        }
    }

}