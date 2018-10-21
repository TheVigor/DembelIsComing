package com.noble.activity.dembeliscoming.fragments.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import androidx.navigation.NavOptions
import androidx.navigation.Navigation.findNavController
import com.noble.activity.dembeliscoming.R
import kotlinx.android.synthetic.main.main_fragment.*

/**
 * Fragment used to show how to navigate to another destination
 */
class MainFragment : Fragment() {
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
    }

}