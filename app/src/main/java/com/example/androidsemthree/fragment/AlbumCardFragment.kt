package com.example.androidsemthree.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.androidsemthree.adapter.AlbumListAdapter
import com.example.androidsemthree.databinding.FragmentCardBinding
import com.example.androidsemthree.rep.AlbumRepository

class AlbumCardFragment : Fragment() {
    private var binding: FragmentCardBinding? = null
    private var albumAdapter: AlbumListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        albumAdapter = AlbumListAdapter(Glide.with(this))
//        binding?.rvAlbums?.run {
//            adapter = albumAdapter
//        }
    }
}
