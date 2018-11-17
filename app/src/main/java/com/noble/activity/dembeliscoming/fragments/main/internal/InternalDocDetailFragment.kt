package com.noble.activity.dembeliscoming.fragments.main.internal

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noble.activity.dembeliscoming.R
import com.noble.activity.dembeliscoming.databinding.FragmentInternalDetailBinding
import com.noble.activity.dembeliscoming.utilities.InjectorUtils
import com.noble.activity.dembeliscoming.viewmodels.internal.InternalDocDetailViewModel

class InternalDocDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val internalDocId = InternalDocDetailFragmentArgs.fromBundle(arguments).internalDocId

        val factory = InjectorUtils.provideInternalDocDetailViewModelFactory(requireActivity(), internalDocId)
        val internalDocDetailViewModel = ViewModelProviders.of(this, factory)
            .get(InternalDocDetailViewModel::class.java)

        val binding = DataBindingUtil.inflate<FragmentInternalDetailBinding>(
            inflater, R.layout.fragment_internal_detail, container, false).apply {
            viewModel = internalDocDetailViewModel
            setLifecycleOwner(this@InternalDocDetailFragment)
        }

        setHasOptionsMenu(true)

        return binding.root
    }

}