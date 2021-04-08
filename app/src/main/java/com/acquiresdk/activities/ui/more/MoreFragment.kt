package com.acquiresdk.activities.ui.more

import android.app.Activity
import android.app.AlarmManager
import android.app.Dialog
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.acquire.sdk.AcquireApp
import com.acquire.sdk.callbacks.OnSessionEvents
import com.acquire.sdk.socket.SocketManager
import com.acquire.sdk.utils.Logger
import com.acquire.sdk.utils.value
import com.acquiresdk.AppApplication.Companion.AcquirePref
import com.acquiresdk.R
import com.acquiresdk.activities.data.model.More
import com.acquiresdk.activities.ui.login.LoginActivity
import com.acquiresdk.activities.ui.main.MainActivity
import com.acquiresdk.adapter.MoreAdapter
import com.acquiresdk.utils.ToolbarUtils
import kotlinx.android.synthetic.main.fragment_more.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.system.exitProcess


class MoreFragment : Fragment(), OnSessionEvents {

    var data = MutableLiveData<List<More>>()
    lateinit var da: List<More>
    var loading: ProgressBar? = null
    var viewLoading: View? = null
    var txtMoreAccountId: TextView? = null
    var prefs: SharedPreferences? = null
    var btMoreChangeId: Button? = null
    var imgLogout: AppCompatImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_more, container, false)
        val recyclerViewMore: RecyclerView = root.findViewById(R.id.recyclerViewMore)
        btMoreChangeId = root.findViewById(R.id.btMoreChangeId)
        imgLogout = root.findViewById(R.id.imgLogout)
        loading = root.findViewById(R.id.loading)
        viewLoading = root.findViewById(R.id.viewLoading)
        txtMoreAccountId = root.findViewById(R.id.txtMoreAccountId)
        val activity = getActivity() as Activity
        prefs = requireActivity().getSharedPreferences(AcquirePref, Context.MODE_PRIVATE)

        val toolbarInstance = ToolbarUtils()
        toolbarInstance.setToolbar(
            activity,
            root,
            getString(R.string.title_more),
            enableBack = false,
            enableSearch = false,
            enableVideo = false,
            enableAudio = false
        )

        AcquireApp.setSessionListener(this)


        recyclerViewMore.layoutManager = LinearLayoutManager(activity)
        val model = ViewModelProvider(this).get(MoreViewModel::class.java)
        model.getData().observe(viewLifecycleOwner, Observer<List<More>> { dataList ->
            // update UI
            data.value = dataList
            da = dataList!!
            recyclerViewMore.adapter = MoreAdapter(activity, data)
        })

        imgLogout?.isClickable = true
        btMoreChangeId?.isClickable = true

        btMoreChangeId?.setOnClickListener { showDialog(activity) }

        imgLogout?.setOnClickListener {
            AcquireApp.logOut {
                // clear local pref
                prefs?.edit()?.clear()?.apply()

                // redirect to login screen
                requireActivity().startActivity(
                    Intent(
                        requireActivity(),
                        LoginActivity::class.java
                    )
                )
                requireActivity().finish()
            }
        }

        setAccountId()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (SocketManager.crmSocket.isSocketAlive()) {
            AcquireApp.onSessionEvents?.onSocketConnected()
        } else {
            AcquireApp.onSessionEvents?.onSocketDisconnected()
        }
    }

    fun setAccountId() {
        txtMoreAccountId?.text =
            prefs?.getString("acc_id", "")
    }

    fun showDialog(ctx: Context) {
        val dialog = Dialog(ctx)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_more)
        val dialogButton: CardView = dialog.findViewById(R.id.btMoreSave) as CardView
        val etMoreDiaId = dialog.findViewById(R.id.etMoreDiaId) as EditText

        dialogButton.setOnClickListener {
            if (etMoreDiaId.value.isNotEmpty()) {
                loading?.visibility = View.VISIBLE
                viewLoading?.visibility = View.VISIBLE
                imgLogout?.isClickable = false
                btMoreChangeId?.isClickable = false

                AcquireApp.logOut {
                    // init new id
                    AcquireApp.init(etMoreDiaId.value)

                    // set new id in local pref
                    val editor = prefs?.edit()
                    editor?.putString("acc_id", etMoreDiaId.value)
                    editor?.apply()

                    restartActivity()

                }
            }
            dialog.dismiss()
        }
        dialog.setCanceledOnTouchOutside(true)
        dialog.show()
        val window = dialog.window as Window
        window.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onSessionConnected() {
        CoroutineScope(Dispatchers.Main).launch {
            Logger.showError("►►► onSessionConnected")

            loading?.visibility = View.GONE
            viewLoading?.visibility = View.GONE
            imgLogout?.isClickable = true
            btMoreChangeId?.isClickable = true

            setAccountId()
        }
    }

    override fun onSessionDisconnected(Reason: String?) {
        Logger.showError("►►► onSessionDisconnected $Reason")
    }


    override fun onAgentAvailable() {
        Logger.showError("►►► onAgentAvailable")
    }

    override fun onTriggerEvent(eventName: String?) {
        Logger.showError("►►► onTriggerEvent $eventName")
    }

    override fun onChatClosed() {
        Logger.showError("►►► onChatClosed")
    }

    override fun noAgentAvailable() {
        Logger.showError("►►► noAgentAvailable")
    }

    override fun onWaitDialogAppear() {
        Logger.showError("►►► onWaitDialogAppear")
    }

    override fun onWaitDialogDisappear() {
        Logger.showError("►►► onWaitDialogDisappear")
    }

    override fun onCallDisconnected() {
        Logger.showError("►►► onCallDisconnected")
    }

    override fun onCallConnected() {
        Logger.showError("►►► onCallConnected")
    }

    override fun onSocketConnected() {
        super.onSocketConnected()

    }

    override fun onSocketDisconnected() {
        super.onSocketDisconnected()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        AcquireApp.setSessionListener(object: OnSessionEvents{
            override fun onSessionConnected() {
            }
        })
    }

    private fun restartActivity() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
             val mStartActivity = Intent(
                requireActivity(),
                MainActivity::class.java
            )
            val mPendingIntentId = 123456
            val mPendingIntent = PendingIntent
                .getActivity(
                    requireActivity(),
                    mPendingIntentId,
                    mStartActivity,
                    PendingIntent.FLAG_CANCEL_CURRENT
                )
            val mgr =
                requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager
            mgr[AlarmManager.RTC, System.currentTimeMillis() + 50] = mPendingIntent
            exitProcess(0)


            /* val mainIntent =
                IntentCompat.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_LAUNCHER)
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            requireActivity().applicationContext.startActivity(mainIntent)
            System.exit(0)*/


           /* val mStartActivity = IntentCompat.makeMainSelectorActivity(
                Intent.ACTION_MAIN,
                Intent.CATEGORY_LAUNCHER
            )
            mStartActivity.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            val mPendingIntentId = 123456
            val mPendingIntent = PendingIntent.getActivity(
                requireActivity().applicationContext,
                mPendingIntentId,
                mStartActivity,
                PendingIntent.FLAG_CANCEL_CURRENT
            )
            val mgr =
                requireActivity().applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            mgr[AlarmManager.RTC, System.currentTimeMillis() + 100] = mPendingIntent
            System.exit(0)*/

        }, 500)
    }
}