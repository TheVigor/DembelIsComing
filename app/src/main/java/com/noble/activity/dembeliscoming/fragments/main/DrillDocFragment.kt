package com.noble.activity.dembeliscoming.fragments.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.noble.activity.dembeliscoming.R
import com.noble.activity.dembeliscoming.adapters.DrillDocAdapter
import com.noble.activity.dembeliscoming.data.DrillDoc
import com.noble.activity.dembeliscoming.databinding.FragmentDrillBinding
import com.noble.activity.dembeliscoming.utilities.InjectorUtils
import com.noble.activity.dembeliscoming.viewmodels.DrillDocListViewModel

class DrillDocFragment : Fragment() {

    private lateinit var viewModel: DrillDocListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDrillBinding.inflate(inflater, container, false)
        val context = context ?: return binding.root

        val factory = InjectorUtils.provideDrillDocListViewModelFactory(context)
        viewModel = ViewModelProviders.of(this, factory).get(DrillDocListViewModel::class.java)

        val adapter = DrillDocAdapter()
        binding.drillDocList.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }


    private fun subscribeUi(adapter: DrillDocAdapter) {
        viewModel.getDrillDocs().observe(viewLifecycleOwner, Observer { drillDocs ->
            if (drillDocs != null) adapter.submitList(drillDocs)
        })
    }

}