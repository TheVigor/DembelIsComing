package com.noble.activity.dembeliscoming.fragments.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import androidx.navigation.NavOptions
import androidx.navigation.Navigation.findNavController
import com.noble.activity.dembeliscoming.*
import com.noble.activity.dembeliscoming.models.stringify
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.timer_main.view.*
import java.util.*

/**
 * Fragment used to show how to navigate to another destination
 */
class MainFragment : Fragment() {

    private val timer = Timer("time_to_dembel", true)

    private val updateInerval: Long = 1000
    private val updateDelay: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val options = NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in_right)
            .setExitAnim(R.anim.slide_out_left)
            .setPopEnterAnim(R.anim.slide_in_left)
            .setPopExitAnim(R.anim.slide_out_right)
            .build()

        timer.scheduleAtFixedRate(object: TimerTask(){
            override fun run() {
                activity?.runOnUiThread {
                    val currentDate = Date().time

                    if (currentDate < soldierPrefs.startDate) {
                        activity?.showToast("Not started yet")
                    }

                    if (currentDate > soldierPrefs.endDate) {
                        activity?.showToast("Yahooo. Dembel was already")
                    }

                    percentText?.text = percentage(currentDate)

                    val diffTimeFromStart = getDiffTime(soldierPrefs.startDate, currentDate)
                    diffTimeFromStartText?.text = diffTimeFromStart.stringify()

                    val diffTimeToDembel = getDiffTime(currentDate, soldierPrefs.endDate)
                    diffTimeToDembelText?.text = diffTimeToDembel.stringify()
                    
                    passedDembelTimer?.updateCounter(diffTimeFromStart, currentDate)


                }
            }
        }, updateDelay, updateInerval)

    }

}