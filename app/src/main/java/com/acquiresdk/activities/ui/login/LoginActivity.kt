package com.acquiresdk.activities.ui.login

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.graphics.drawable.DrawableCompat
import com.acquire.sdk.AcquireApp
import com.acquire.sdk.callbacks.OnSessionEvents
import com.acquire.sdk.utils.StatusIconColorType
import com.acquire.sdk.utils.setStatusBarColor
import com.acquire.sdk.utils.value
import com.acquiresdk.AppApplication.Companion.AcquirePref
import com.acquiresdk.R
import com.acquiresdk.activities.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginActivity : AppCompatActivity(), OnSessionEvents {

    var accountId = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor(Color.WHITE, StatusIconColorType.Dark)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.etUsername)
        val login = findViewById<CardView>(R.id.cvLogin)
        val tvLoginConnect = findViewById<TextView>(R.id.tvLoginConnect)
        val pbLogin = findViewById<ProgressBar>(R.id.pbLogin)

        tvLoginConnect.visibility = View.VISIBLE
        pbLogin.visibility = View.GONE

        AcquireApp.setSessionListener(this)

        login.setOnClickListener {
            if (username.value.isNotEmpty()) {
                accountId = username.value
                // init id
                AcquireApp.init(accountId)

                tvLoginConnect.visibility = View.GONE
                pbLogin.visibility = View.VISIBLE

                DrawableCompat.setTint(
                    pbLogin.indeterminateDrawable,
                    Color.WHITE
                )
            }
        }
    }

    override fun onSessionConnected() {
        CoroutineScope(Dispatchers.IO).launch {
            val prefs = getSharedPreferences(AcquirePref, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString("acc_id", accountId)
            editor.apply()

            startActivity(
                Intent(
                    this@LoginActivity,
                    MainActivity::class.java
                ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
            finish()
        }
    }


}
