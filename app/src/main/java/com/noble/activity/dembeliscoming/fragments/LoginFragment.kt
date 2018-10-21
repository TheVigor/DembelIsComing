package com.noble.activity.dembeliscoming.fragments

import android.app.DatePickerDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController

import com.noble.activity.dembeliscoming.R
import com.noble.activity.dembeliscoming.soldierPrefs
import com.noble.activity.dembeliscoming.updateDate
import com.noble.activity.dembeliscoming.validateNewSoldier
import kotlinx.android.synthetic.main.fragment_login.*
import java.text.SimpleDateFormat
import java.time.Month
import java.time.Year
import java.util.*

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        start_date_btn.setOnClickListener {
            DatePickerDialog(context,
                DatePickerDialog.OnDateSetListener{view, mYear, mMonth, mDay ->
                    //(it as Button).text = "${mDay}.${mMonth}.${mYear}"
                    updateDate(it, mDay, mMonth, mYear, true)
                }, year, month, day).show()

        }

        end_date_btn.setOnClickListener {
            DatePickerDialog(context,
                DatePickerDialog.OnDateSetListener{view, mYear, mMonth, mDay ->
                    //(it as Button).text = "${mDay}.${mMonth}.${mYear}"
                    updateDate(it, mDay, mMonth, mYear, false)
                }, year, month, day).show()
        }

        login_btn.setOnClickListener {
            soldierPrefs.soldierName = name_input.text.toString().trim()

            if (!validateNewSoldier(context!!))
                return@setOnClickListener

            findNavController(it).navigate(R.id.mainActivity)
            activity?.finish()
        }
    }

}