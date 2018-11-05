package com.noble.activity.dembeliscoming.fragments.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.view.*
import com.noble.activity.dembeliscoming.R
import com.noble.activity.dembeliscoming.databinding.FragmentDrillDetailBinding
import com.noble.activity.dembeliscoming.utilities.InjectorUtils
import com.noble.activity.dembeliscoming.viewmodels.DrillDocDetailViewModel

class DrillDocDetailFragment : Fragment() {

    private lateinit var shareText: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val plantId = DrillDocDetailFragmentArgs.fromBundle(arguments).drillDocId

        val factory = InjectorUtils.provideDrillDocDetailViewModelFactory(requireActivity(), plantId)
        val drillDocDetailViewModel = ViewModelProviders.of(this, factory)
            .get(DrillDocDetailViewModel::class.java)

        val binding = DataBindingUtil.inflate<FragmentDrillDetailBinding>(
            inflater, R.layout.fragment_drill_detail, container, false).apply {
            viewModel = drillDocDetailViewModel
            setLifecycleOwner(this@DrillDocDetailFragment)
        }

        drillDocDetailViewModel.drillDoc.observe(this, Observer { drillDoc ->
            shareText = if (drillDoc == null) {
                ""
            } else {
                drillDoc.name
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

}
