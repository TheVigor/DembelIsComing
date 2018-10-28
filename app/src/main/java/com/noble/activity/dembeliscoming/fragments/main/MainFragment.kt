package com.noble.activity.dembeliscoming.fragments.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import androidx.navigation.NavOptions
import androidx.navigation.Navigation.findNavController
import com.noble.activity.dembeliscoming.R
import com.noble.activity.dembeliscoming.getDiffTime
import com.noble.activity.dembeliscoming.showToast
import com.noble.activity.dembeliscoming.soldierPrefs
import kotlinx.android.synthetic.main.main_fragment.*
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

        navigate_dest_bt.setOnClickListener {
            findNavController(it).navigate(R.id.settingsFragment, null, options)
        }

        navigate_action_bt.setOnClickListener {
            findNavController(it).navigate(R.id.action_mainFragment_to_settingsFragment2)
        }

        timer.scheduleAtFixedRate(object: TimerTask(){
            override fun run() {
                activity?.runOnUiThread {
                    val cal = Date()
                    val currentDate = cal.time

                    if (currentDate < soldierPrefs.startDate) {
                        activity?.showToast("Not started yet")
                    }

                    if (currentDate > soldierPrefs.endDate) {
                        activity?.showToast("Yahooo. Dembel was already")
                    }

                    val diffTimeFromStart = getDiffTime(soldierPrefs.startDate, currentDate)
                    diffTimeFromStartText?.text = "${diffTimeFromStart.days} ${diffTimeFromStart.hours} " +
                            "${diffTimeFromStart.minutes} ${diffTimeFromStart.seconds}"


                    val diffTimeToDembel = getDiffTime(currentDate, soldierPrefs.endDate)
                    diffTimeToDembelText?.text = "${diffTimeToDembel.days} ${diffTimeToDembel.hours} " +
                            "${diffTimeToDembel.minutes} ${diffTimeToDembel.seconds}"
                }
            }
        }, updateDelay, updateInerval)

    }

}