package com.example.androidsemthree.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidsemthree.adapter.AlbumCardViewAdapter
import com.example.androidsemthree.databinding.FragmentCardBinding
import com.example.androidsemthree.decorator.ItemCardDecorator
import com.example.androidsemthree.decorator.ItemDecorator
import com.example.androidsemthree.rep.AlbumRepository

class AlbumCardFragment : Fragment() {
    private lateinit var binding: FragmentCardBinding
    private lateinit var albumCardViewAdapter: AlbumCardViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        albumCardViewAdapter = AlbumCardViewAdapter(AlbumRepository.getList(), Glide.with(this))

        val decorator = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        val spacing = ItemCardDecorator(requireContext())

        with(binding) {
            rvAlbums.run {
                adapter = albumCardViewAdapter
                addItemDecoration(decorator)
                addItemDecoration(spacing)
            }
        }
    }
}

