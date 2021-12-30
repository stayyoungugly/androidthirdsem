package com.example.androidsemthree.fragments

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidsemthree.R
import com.example.androidsemthree.adapter.NoteAdapter
import com.example.androidsemthree.database.AppDatabase
import com.example.androidsemthree.databinding.FragmentNoteListBinding
import com.example.androidsemthree.models.Note
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class NoteListFragment : Fragment(R.layout.fragment_note_list) {
    private var binding: FragmentNoteListBinding? = null
    private var database: AppDatabase? = null
    private var noteAdapter: NoteAdapter? = null
    private var notes: List<Note>? = null

    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNoteListBinding.bind(view)
        database = AppDatabase.invoke(context) as AppDatabase
        noteAdapter = NoteAdapter({ showNoteFragment(it) }, { deleteNote(it) })

        binding?.apply {
            toolBar.setOnMenuItemClickListener {
                onOptionsItemSelected(it)
            }
            fabAdd.setOnClickListener {
                showNoteFragment(null)
            }
            rvNotes.run {
                adapter = noteAdapter
            }
        }
        updateNotes()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.icon_delete_all -> {
                deleteAllNotes()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteAllNotes() {
        scope.launch {
            database?.noteDao()?.deleteAllNotes()
        }
        updateNotes()
        showMessage("Задачи удалены")
    }

    private fun deleteNote(id: Int) {
        scope.launch {
            database?.noteDao()?.deleteNoteById(id)
        }
        updateNotes()
        showMessage("Задача удалена")
    }

    private fun updateNotes() {
        scope.launch {
            notes = database?.noteDao()?.getAll()
        }
        binding?.apply {
            if (notes.isNullOrEmpty()) {
                rvNotes.visibility = View.GONE
                noNotesAdded.visibility = View.VISIBLE
            } else {
                rvNotes.visibility = View.VISIBLE
                noNotesAdded.visibility = View.GONE
            }
        }
        noteAdapter?.submitList(ArrayList(notes))
    }

    private fun showNoteFragment(id: Int?) {
        var bundle: Bundle? = null
        id?.also {
            bundle = Bundle().apply {
                putInt("ARG_NOTE_ID", id)
            }
        }
        findNavController().navigate(
            R.id.action_noteListFragment_to_noteDetailsFragment,
            bundle
        )
    }

    private fun showMessage(message: String) {
        Snackbar.make(
            requireActivity().findViewById(R.id.container),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        database = null
        noteAdapter = null
        scope.cancel()
    }
}
