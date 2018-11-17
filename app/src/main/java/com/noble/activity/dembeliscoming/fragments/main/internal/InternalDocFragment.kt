package com.noble.activity.dembeliscoming.fragments.main.internal

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noble.activity.dembeliscoming.adapters.internal.InternalDocAdapter
import com.noble.activity.dembeliscoming.databinding.FragmentInternalBinding
import com.noble.activity.dembeliscoming.utilities.InjectorUtils
import com.noble.activity.dembeliscoming.viewmodels.internal.InternalDocListViewModel

class InternalDocFragment : Fragment() {

    private lateinit var viewModel: InternalDocListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentInternalBinding.inflate(inflater, container, false)
        val context = context ?: return binding.root

        val factory = InjectorUtils.provideInternalDocListViewModelFactory(context)
        viewModel = ViewModelProviders.of(this, factory).get(InternalDocListViewModel::class.java)

        val adapter = InternalDocAdapter()
        binding.internalDocList.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }


    private fun subscribeUi(adapter: InternalDocAdapter) {
        viewModel.getInternalDocs().observe(viewLifecycleOwner, Observer { internalDocs ->
            if (internalDocs != null) adapter.submitList(internalDocs)
        })
    }

}