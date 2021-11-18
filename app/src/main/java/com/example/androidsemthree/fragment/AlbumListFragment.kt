package com.example.androidsemthree.fragment

import SwipeToDelete
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidsemthree.adapter.AlbumListAdapter
import com.example.androidsemthree.databinding.FragmentListBinding
import com.example.androidsemthree.decorator.ItemDecorator
import com.example.androidsemthree.dialogs.AddDialog
import com.example.androidsemthree.rep.AlbumRepository
import com.google.android.material.snackbar.Snackbar

class AlbumListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var albumListAdapter: AlbumListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        albumListAdapter = AlbumListAdapter(Glide.with(this)) {
            if (it != null) {
                AlbumRepository.delete(it)
                refresh()
            }
        }

        val decorator = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        val spacing = ItemDecorator(requireContext())

        binding.rvAlbums.run {
            adapter = albumListAdapter
            addItemDecoration(spacing)
            addItemDecoration(decorator)
            swipeToDelete(this)
        }
        refresh()
        binding.btnAdd.setOnClickListener {
            showDialog()
        }

    }

    private fun swipeToDelete(recyclerView: RecyclerView) {
        val swipeToDeleteCallback = object : SwipeToDelete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = albumListAdapter.currentList[viewHolder.absoluteAdapterPosition]
                AlbumRepository.delete(AlbumRepository.getIndex(item))
                refresh()
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun refresh() {
        albumListAdapter.submitList(AlbumRepository.getList())
    }

    private fun showDialog() {
        AddDialog.show(
            childFragmentManager,
            positive = { positiveAddDialog(it) },
            notFull = { notFullAlert(it) })
    }

    private fun positiveAddDialog(array: Array<String>) {
        val title = array[0]
        val desc = array[1]
        val pos: Int? = if (array[2].isEmpty()) {
            null
        } else {
            array[2].toInt()
        }
        AlbumRepository.add(pos, title, desc)
        refresh()
    }

    private fun notFullAlert(flag: Boolean) {
        if (!flag) {
            Snackbar.make(
                binding.root,
                "Please, enter all data and try again",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}

