package com.noble.activity.dembeliscoming.fragments.main.note

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.noble.activity.dembeliscoming.adapters.note.NoteAdapter
import com.noble.activity.dembeliscoming.databinding.FragmentNoteBinding
import com.noble.activity.dembeliscoming.utilities.InjectorUtils
import com.noble.activity.dembeliscoming.viewmodels.note.NoteListViewModel
import kotlinx.android.synthetic.main.fragment_note.*

class NoteFragment : Fragment() {

    private lateinit var viewModel: NoteListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNoteBinding.inflate(inflater, container, false)
        val context = context ?: return binding.root

        val factory = InjectorUtils.provideNoteListViewModelFactory(context)
        viewModel = ViewModelProviders.of(this, factory).get(NoteListViewModel::class.java)

        val adapter = NoteAdapter()
        binding.noteList.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab_add_note?.setOnClickListener{
            val direction = NoteFragmentDirections.ActionNoteFragmentToNoteAddEditFragment("")
            findNavController().navigate(direction)
        }
    }

    private fun subscribeUi(adapter: NoteAdapter) {
        viewModel.getNotes().observe(viewLifecycleOwner, Observer { notes ->
            if (notes != null) adapter.submitList(notes)
        })
    }

}