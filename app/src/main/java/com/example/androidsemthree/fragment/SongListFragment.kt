package com.example.androidsemthree.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidsemthree.adapter.SongListAdapter
import com.example.androidsemthree.databinding.FragmentSongListBinding
import com.example.androidsemthree.repository.SongsRepository

class SongListFragment : Fragment() {
    private var binding: FragmentSongListBinding? = null
    private var songListAdapter: SongListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSongListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        songListAdapter = SongListAdapter(SongsRepository.songsList) {
            val directions = SongListFragmentDirections.actionSongListFragmentToSongFragment(
                it
            )
            findNavController().navigate(directions)
        }
        binding?.rvSongs?.run {
            adapter = songListAdapter
        }
    }
}
