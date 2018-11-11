package com.noble.activity.dembeliscoming.fragments.main

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.noble.activity.dembeliscoming.R
import com.noble.activity.dembeliscoming.soldierPrefs
import com.noble.activity.dembeliscoming.utilities.*
import kotlinx.android.synthetic.main.fragment_settings.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment : Fragment() {

    private lateinit var startCalendar: Calendar
    private lateinit var endCalendar: Calendar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startCalendar = Calendar.getInstance()
        startCalendar.time = Date(soldierPrefs.startDate)

        endCalendar = Calendar.getInstance()
        endCalendar.time = Date(soldierPrefs.endDate)

        name_input.setText(soldierPrefs.soldierName)
        start_date_btn.text = mkTxtDateByLong(soldierPrefs.startDate)
        end_date_btn.text = mkTxtDateByLong(soldierPrefs.endDate)

        start_date_btn.setOnClickListener {
            DatePickerDialog(context,
                DatePickerDialog.OnDateSetListener{ view, mYear, mMonth, mDay ->
                    startCalendar = mkCalendar(mDay, mMonth, mYear)
                    start_date_btn.text = mkTxtDateByDMY(mDay, mMonth, mYear)
                }, startCalendar.get(Calendar.YEAR), startCalendar.get(Calendar.MONTH),
                startCalendar.get(Calendar.DAY_OF_MONTH)).show()

        }

        end_date_btn.setOnClickListener {
            DatePickerDialog(context,
                DatePickerDialog.OnDateSetListener{ view, mYear, mMonth, mDay ->
                    endCalendar = mkCalendar(mDay, mMonth, mYear)
                    end_date_btn.text = mkTxtDateByDMY(mDay, mMonth, mYear)
                }, endCalendar.get(Calendar.YEAR), endCalendar.get(Calendar.MONTH),
                endCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        update_btn.setOnClickListener {
            soldierPrefs.soldierName = name_input.text.toString().trim()

            if (!validateUpdatedSoldier(context!!, name_input.text.toString(), startCalendar,
                    endCalendar))
                return@setOnClickListener

            updateSoldier(name_input.text.toString(), startCalendar, endCalendar)
            activity?.onBackPressed()
        }
    }
}