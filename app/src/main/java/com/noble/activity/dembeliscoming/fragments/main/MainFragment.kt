package com.noble.activity.dembeliscoming.fragments.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.noble.activity.dembeliscoming.*
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.timer_main.view.*
import java.util.*
import com.noble.activity.dembeliscoming.utilities.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header.view.*

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
        
        activity?.navigation_view?.getHeaderView(0)?.header_name?.text = soldierPrefs.soldierName

        chart?.init()

        finishedText?.visibility = View.GONE

        notStartedTimer?.title_text?.text = "Time to start"
        notStartedTimer?.timer_icon?.setImageResource(R.drawable.sad_soldier)
        notStartedTimer?.visibility = View.GONE

        passedDembelTimer?.title_text?.text = "Passed"
        passedDembelTimer?.timer_icon?.setImageResource(R.drawable.happy_soldier)

        leftDembelTimer?.title_text?.text = "Left"
        leftDembelTimer?.timer_icon?.setImageResource(R.drawable.sad_soldier)

        timer.scheduleAtFixedRate(object: TimerTask(){
            override fun run() {
                activity?.runOnUiThread {
                    val currentDate = Date().time

                    if (currentDate < soldierPrefs.startDate) {

                        notStartedTimer?.visibility = View.VISIBLE

                        passedDembelTimer?.visibility = View.GONE
                        leftDembelTimer?.visibility = View.GONE
                        chart?.visibility = View.GONE
                        finishedText?.visibility = View.GONE

                        val diffTimeToStart =
                            getDiffTime(currentDate, soldierPrefs.startDate)

                        notStartedTimer?.updateCounter(diffTimeToStart)
                        notStartedTimer?.percentage_text?.text =
                                "Starting date: ${mkTxtDateByLong(soldierPrefs.startDate)}"

                        return@runOnUiThread
                    }

                    if (currentDate >= soldierPrefs.endDate) {

                        finishedText?.visibility = View.VISIBLE

                        passedDembelTimer?.visibility = View.GONE
                        leftDembelTimer?.visibility = View.GONE
                        chart?.visibility = View.GONE
                        notStartedTimer?.visibility = View.GONE

                        return@runOnUiThread
                    }

                    notStartedTimer?.visibility = View.GONE
                    finishedText?.visibility = View.GONE

                    passedDembelTimer?.visibility = View.VISIBLE
                    leftDembelTimer?.visibility = View.VISIBLE
                    chart?.visibility = View.VISIBLE


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