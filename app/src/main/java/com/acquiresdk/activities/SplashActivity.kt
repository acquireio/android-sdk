package com.acquiresdk.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.acquire.sdk.AcquireApp
import com.acquiresdk.AppApplication
import com.acquiresdk.R
import com.acquiresdk.activities.ui.login.LoginActivity
import com.acquiresdk.activities.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({

            val prefs =
                getSharedPreferences(AppApplication.AcquirePref, Context.MODE_PRIVATE)
            val accountID = prefs.getString("acc_id", null)
            if (!accountID.isNullOrEmpty()) {
                AcquireApp.init(AppApplication.application, accountID)
                startActivity(
                    Intent(
                        this,
                        MainActivity::class.java
                    ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                )
            } else {
                startActivity(
                    Intent(
                        this,
                        LoginActivity::class.java
                    ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                )
            }

            finish()
        }, 700)
    }
}