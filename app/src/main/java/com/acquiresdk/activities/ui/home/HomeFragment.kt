package com.acquiresdk.activities.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.acquiresdk.R
import com.acquiresdk.activities.ui.cart.CartActivity
import com.acquiresdk.activities.ui.cobrowse.CoBrowseActivity
import com.acquiresdk.activities.ui.inapp.InAppActivity

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val activity = activity as Context

        val viewWidgetExp = root.findViewById(R.id.viewWidgetExp) as View
        val viewCoBrowseExp = root.findViewById(R.id.viewCoBrowseExp) as View
        val viewBgInApp = root.findViewById(R.id.viewBgInApp) as View
        viewWidgetExp.setOnClickListener {
            activity.startActivity(Intent(activity, CartActivity::class.java))
        }
        viewCoBrowseExp.setOnClickListener {
            activity.startActivity(Intent(activity, CoBrowseActivity::class.java))
        }
        viewBgInApp.setOnClickListener {
            activity.startActivity(Intent(activity, InAppActivity::class.java))
        }
        return root
    }
}