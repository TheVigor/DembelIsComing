package com.noble.activity.dembeliscoming.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import com.noble.activity.dembeliscoming.R
import com.noble.activity.dembeliscoming.soldierPrefs
import kotlinx.android.synthetic.main.fragment_splash.*


class SplashFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        next_btn.setOnClickListener {
            if (soldierPrefs.isSoldierLoggedIn()) {
                findNavController(it).navigate(R.id.mainActivity)
            } else {
                findNavController(it).navigate(R.id.splashToLogin)
            }
        }
    }
}
