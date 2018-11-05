package com.noble.activity.dembeliscoming.fragments.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import androidx.navigation.NavOptions
import com.noble.activity.dembeliscoming.*
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.timer_main.view.*
import java.util.*
import com.noble.activity.dembeliscoming.utilities.*


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

        chart?.init()

        passedDembelTimer?.title_text?.text = "Passed"
        passedDembelTimer?.timer_icon?.setImageResource(R.drawable.happy_soldier)

        leftDembelTimer?.title_text?.text = "Left"
        leftDembelTimer?.timer_icon?.setImageResource(R.drawable.sad_soldier)

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

                    val diffTimeFromStart =
                        getDiffTime(soldierPrefs.startDate, currentDate)
                    val diffTimeToDembel =
                        getDiffTime(currentDate, soldierPrefs.endDate)

                    passedDembelTimer?.updateCounter(diffTimeFromStart)
                    passedDembelTimer?.updatePassedPercentage(currentDate)

                    leftDembelTimer?.updateCounter(diffTimeToDembel)
                    leftDembelTimer?.updateLeftPercentage(currentDate)

                    chart?.updateData(
                        percentagePassed(currentDate).replace(',', '.').toFloat(),
                        percentageLeft(currentDate).replace(',', '.').toFloat())

                }
            }
        }, updateDelay, updateInerval)

    }

}