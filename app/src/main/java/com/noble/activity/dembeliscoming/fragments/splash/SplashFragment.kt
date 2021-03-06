package com.noble.activity.dembeliscoming.fragments.splash

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils.loadAnimation
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

        val fadingText: Animation = loadAnimation(context, R.anim.fade_in)
        val fadingLogo: Animation = loadAnimation(context, R.anim.fade_in)
        splash_text.startAnimation(fadingText)
        splash_logo.startAnimation(fadingLogo)

        fadingLogo.setAnimationListener(object : Animation.AnimationListener
        {
            override fun onAnimationRepeat(p0: Animation?) {}
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                if (soldierPrefs.isSoldierLoggedIn()) {

//                    val intent = Intent(context, MainActivity::class.java)
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
//                            or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                    startActivity(intent)

                    findNavController(splash_logo).navigate(R.id.splashToMain)
                    activity?.finish()
                } else {
                    findNavController(splash_logo).navigate(R.id.splashToLogin)
                }
            }
        })

    }
}
