package com.noble.activity.dembeliscoming.fragments.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noble.activity.dembeliscoming.R
import com.vansuita.materialabout.builder.AboutBuilder
import com.vansuita.materialabout.views.AboutView
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadAbout()
    }

    fun loadAbout() {
        val aboutBuilder: AboutBuilder = AboutBuilder.with(activity)
            .setAppIcon(R.mipmap.about_icon)
            .setAppName(R.string.app_name)
            .setPhoto(R.mipmap.profile_picture)
            .setCover(R.mipmap.profile_cover)
            .setLinksAnimated(true)
            .setDividerDashGap(13)
            .setName("Name")
            .setSubTitle("Mobile Developer")
            .setLinksColumnsCount(2)
            .setBrief("I'm warmed of mobile technologies. Ideas maker, curious and nature lover.")
            .addGitHubLink("TheVigor")
            .addEmailLink("the.noble.activity@gmail.com")
            .addFiveStarsAction()
            .addMoreFromMeAction("Noble Activity")
            .setVersionNameAsAppSubTitle()
            .addShareAction(R.string.app_name)
            .setActionsColumnsCount(2)
            .addFeedbackAction("the.noble.activity@gmail.com")
            .setWrapScrollView(true)
            .setShowAsCard(true)

        val aboutView: AboutView = aboutBuilder.build()

        constraint_layout.addView(aboutView)
    }
}