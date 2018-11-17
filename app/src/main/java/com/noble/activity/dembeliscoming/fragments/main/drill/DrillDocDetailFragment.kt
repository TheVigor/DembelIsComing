package com.noble.activity.dembeliscoming.fragments.main.drill

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.*
import com.noble.activity.dembeliscoming.R
import com.noble.activity.dembeliscoming.databinding.FragmentDrillDetailBinding
import com.noble.activity.dembeliscoming.utilities.InjectorUtils
import com.noble.activity.dembeliscoming.viewmodels.drill.DrillDocDetailViewModel

class DrillDocDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val drillDocId = DrillDocDetailFragmentArgs.fromBundle(arguments).drillDocId

        val factory = InjectorUtils.provideDrillDocDetailViewModelFactory(requireActivity(), drillDocId)
        val drillDocDetailViewModel = ViewModelProviders.of(this, factory)
            .get(DrillDocDetailViewModel::class.java)

        val binding = DataBindingUtil.inflate<FragmentDrillDetailBinding>(
            inflater, R.layout.fragment_drill_detail, container, false).apply {
            viewModel = drillDocDetailViewModel
            setLifecycleOwner(this@DrillDocDetailFragment)
        }

        setHasOptionsMenu(true)

        return binding.root
    }

}
