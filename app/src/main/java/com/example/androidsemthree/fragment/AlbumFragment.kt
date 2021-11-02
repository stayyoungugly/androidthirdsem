package com.example.androidsemthree.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.androidsemthree.databinding.FragmentAlbumBinding
import com.example.androidsemthree.rep.AlbumRepository


class AlbumFragment : Fragment() {

    private lateinit var binding: FragmentAlbumBinding
    private val args: AlbumFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = args.albumID
        val album = AlbumRepository.getAlbumById(id)
        with(binding) {
            Glide.with(this.root).load(album.url).into(ivTool)
            tvName.text = "NAME: ${album.name}"
            tvAuthor.text = "AUTHOR: ${album.author}"
            tvDesc.text = "DESCRIPTION: ${album.description}"
            tvYear.text = "YEAR: ${album.year}"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
