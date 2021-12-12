package com.example.androidsemthree.service

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.IBinder
import com.example.androidsemthree.ISongsInterface
import com.example.androidsemthree.repository.SongsRepository

class MusicService : Service() {
    private var currentSongId: Int = 0
    private lateinit var notificationService: NotificationService

    private var mediaPlayer: MediaPlayer = MediaPlayer().apply {
        setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )
    }

    override fun onCreate() {
        super.onCreate()
        currentSongId = 0
        notificationService = NotificationService(this)
        notificationService.setNotification(currentSongId)
    }

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {
        when (intent?.action) {
            "PREVIOUS" -> {
              playPreviousSong()
            }
            "RESUME" -> {
                if (mediaPlayer.isPlaying) pauseSong() else playSong()
            }
            "NEXT" -> {
                playNextSong()
            }
            "STOP" -> {
                stop()
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    inner class AidlBinder : ISongsInterface.Stub() {

        override fun playPreviousSong() {
            this@MusicService.playPreviousSong()
        }

        override fun playNextSong() {
            this@MusicService.playNextSong()
        }

        override fun pauseSong() {
            this@MusicService.pauseSong()
        }

        override fun playSong() {
            this@MusicService.playSong()
        }

        override fun setSong(id: Int) {
            this@MusicService.setSong(id)
        }

        override fun stop() {
            this@MusicService.stop()
        }

    }

    private fun playPreviousSong() {
        currentSongId.let { id ->
            currentSongId = if (id == 0) {
                SongsRepository.songsList.size - 1
            } else {
                id - 1
            }
        }
        setSong(currentSongId)
        playSong()
    }

    private fun playNextSong() {
        currentSongId.let { id ->
            currentSongId = if (id == SongsRepository.songsList.size - 1) {
                0
            } else {
                id + 1
            }
        }
        setSong(currentSongId)
        playSong()
    }

    private fun setSong(id: Int) {
        if (mediaPlayer.isPlaying) mediaPlayer.stop()
        mediaPlayer = MediaPlayer.create(applicationContext, SongsRepository.songsList[id].sound)
        currentSongId = id
        mediaPlayer.run {
            setOnCompletionListener {
                stop()
            }
        }
        notificationService.setNotification(id)
    }

    private fun playSong() {
        mediaPlayer.start()
        notificationService.setState("PLAY")
    }

    private fun pauseSong() {
        mediaPlayer.pause()
        notificationService.setState("PAUSE")
    }

    private fun stop() {
        mediaPlayer.stop()
        notificationService.cancelNotification()
    }

    override fun onBind(intent: Intent?): IBinder = AidlBinder()

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}
