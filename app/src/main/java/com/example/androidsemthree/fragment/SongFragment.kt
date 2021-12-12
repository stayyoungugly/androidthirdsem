package com.example.androidsemthree.fragment

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.androidsemthree.ISongsInterface
import com.example.androidsemthree.R
import com.example.androidsemthree.databinding.FragmentSongBinding
import com.example.androidsemthree.model.Song
import com.example.androidsemthree.repository.SongsRepository
import com.example.androidsemthree.service.MusicService

class SongFragment : Fragment(R.layout.fragment_song) {
    private var binding: FragmentSongBinding? = null
    private var binderAidl: ISongsInterface? = null

    private val connectionAidl = object : ServiceConnection {
        override fun onServiceConnected(
            name: ComponentName?,
            service: IBinder?
        ) {
            binderAidl = ISongsInterface.Stub.asInterface(service)
            initView()
        }

        override fun onServiceDisconnected(
            name: ComponentName?
        ) {
            binderAidl = null
        }
    }

    private var currentSong: Song? = null
    private var playing: Boolean = true

    private fun initView() {
        val id = arguments?.getInt("songID")
        id?.let {
            currentSong = SongsRepository.songsList[id]
        }

        binding?.run {
            tvSongTitle.text = currentSong?.title
            tvSongAuthor.text = currentSong?.author
            currentSong?.cover?.let { ivSongCover.setImageResource(it) }
        }
        initMusicNavigationView(id)
    }

    private fun initMusicNavigationView(id: Int?) {
        id?.let { id ->
            binderAidl?.setSong(id)
            binderAidl?.playSong()

            binding?.run {
                btPlay.setOnClickListener {
                    play()
                }
                btPrev.setOnClickListener {
                    previous(id)
                }
                btNext.setOnClickListener {
                    next(id)
                }
                btPause.setOnClickListener {
                    pause()
                }
                btStop.setOnClickListener {
                    stop()
                }
            }
        }
    }

    private fun pause() {
        binderAidl?.pauseSong()
        playing = false
        showPlayButton()
    }

    private fun stop() {
        binderAidl?.stop()
        playing = false
        showPlayButton()
    }

    private fun next(id: Int) {
        binderAidl?.playNextSong()
        if (id == SongsRepository.songsList.size - 1) {
            updateView(0)
        } else {
            updateView(id + 1)
        }
    }

    private fun previous(id: Int) {
        binderAidl?.playPreviousSong()
        if (id == 0) {
            updateView(SongsRepository.songsList.size - 1)
        } else {
            updateView(id - 1)
        }
    }

    private fun play() {
        binderAidl?.playSong()
        playing = true
        showPauseButton()
    }

    private fun updateView(id: Int) {
        id.let {
            currentSong = SongsRepository.songsList[id]
        }

        binding?.run {
            tvSongTitle.text = currentSong?.title
            tvSongAuthor.text = currentSong?.author
            currentSong?.cover?.let { ivSongCover.setImageResource(it) }
        }

        showPauseButton()
        initMusicNavigationView(id)
    }


    private fun showPauseButton() {
        binding?.run {
            btPlay.visibility = View.INVISIBLE
            btPause.visibility = View.VISIBLE
        }
    }

    private fun showPlayButton() {
        binding?.run {
            btPause.visibility = View.INVISIBLE
            btPlay.visibility = View.VISIBLE
        }
    }


    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {
        binding = FragmentSongBinding.inflate(inflater, container, false)
        activity?.bindService(
            Intent(activity, MusicService::class.java),
            connectionAidl,
            AppCompatActivity.BIND_AUTO_CREATE
        )
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        binderAidl = null
    }
}
