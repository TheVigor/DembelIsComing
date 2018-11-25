package com.noble.activity.dembeliscoming.fragments.main.note

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noble.activity.dembeliscoming.R
import com.noble.activity.dembeliscoming.databinding.FragmentNoteAddEditBinding
import com.noble.activity.dembeliscoming.utilities.InjectorUtils
import com.noble.activity.dembeliscoming.utilities.runOnIoThread
import com.noble.activity.dembeliscoming.viewmodels.note.NoteAddEditViewModel
import kotlinx.android.synthetic.main.fragment_note_add_edit.*

class NoteAddEditFragment : Fragment() {

//    private fun loadData() {
//        // Add or edit an existing task?
//        viewDataBinding.viewmodel?.start(arguments?.getString(ARGUMENT_EDIT_TASK_ID))
//    }

    private lateinit var viewDataBinding: FragmentNoteAddEditBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val noteId = NoteAddEditFragmentArgs.fromBundle(arguments).noteId

        val factory = InjectorUtils.provideNoteAddEditViewModelFactory(requireActivity(), noteId)
        val noteAddEditViewModel = ViewModelProviders.of(this, factory)
            .get(NoteAddEditViewModel::class.java)

        viewDataBinding = DataBindingUtil.inflate<FragmentNoteAddEditBinding>(
            inflater, R.layout.fragment_note_add_edit, container, false
        ).apply {
            viewModel = noteAddEditViewModel
            setLifecycleOwner(this@NoteAddEditFragment)
        }

        setHasOptionsMenu(true)

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab_edit_task_done?.setOnClickListener {
            runOnIoThread {
                viewDataBinding.viewModel?.saveTask(note_title.text.toString(), note_description.text.toString())
            }
        }
    }
}
