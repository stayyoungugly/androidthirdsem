package com.example.androidsemthree.fragment

import com.example.androidsemthree.adapter.AlbumAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidsemthree.R
import com.example.androidsemthree.databinding.FragmentMainBinding
import com.example.androidsemthree.rep.AlbumRepository

class MainFragment : Fragment() {
    private var binding: FragmentMainBinding? = null
    private var albumAdapter: AlbumAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        albumAdapter = AlbumAdapter(AlbumRepository.albums, Glide.with(this)) {
            val directions = MainFragmentDirections.actionMainFragmentToAlbumFragment(
                it
            )
            findNavController().navigate(directions)
        }
        binding?.rvAlbums?.run {
            adapter = albumAdapter
        }
    }
}

