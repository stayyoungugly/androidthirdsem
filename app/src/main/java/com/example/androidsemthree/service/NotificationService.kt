package com.example.androidsemthree.service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.example.androidsemthree.R
import com.example.androidsemthree.activity.MainActivity
import com.example.androidsemthree.repository.SongsRepository

private const val CHANNEL_ID = "music_channel"

class NotificationService (
    private val context: Context
) {

    private var builder: NotificationCompat.Builder

    private val manager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    private val prevIntent = Intent(context, MusicService::class.java).apply {
        action = "PREVIOUS"
    }.let {
        PendingIntent.getService(
            context,
            0,
            it,
            0
        )
    }
    private val resumeIntent = Intent(context, MusicService::class.java).apply {
        action = "RESUME"
    }.let {
        PendingIntent.getService(
            context,
            1,
            it,
            0
        )
    }
    private val nextIntent = Intent(context, MusicService::class.java).apply {
        action = "NEXT"
    }.let {
        PendingIntent.getService(
            context,
            2,
            it,
            0
        )
    }
    private val stopIntent = Intent(context, MusicService::class.java).apply {
        action = "STOP"
    }.let {
        PendingIntent.getService(
            context,
            3,
            it,
            0
        )
    }

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                CHANNEL_ID,
                context.getString(R.string.notification_channel_title),
                IMPORTANCE_HIGH
            ).apply {
                description = context.getString(R.string.notification_channel_description)

            }.also {
                manager.createNotificationChannel(it)
            }
        }

        builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_library_music_24)
            .addAction(R.drawable.ic_baseline_skip, "PREVIOUS", prevIntent)
            .addAction(R.drawable.ic_baseline_paused, "RESUME", resumeIntent)
            .addAction(R.drawable.ic_baseline_next, "NEXT", nextIntent)
            .addAction(R.drawable.ic_baseline_cancel, "STOP", stopIntent)

    }

    fun setNotification(currentSongId: Int) {
        val song = SongsRepository.songsList[currentSongId]
        val icon = BitmapFactory.decodeResource(context.resources, song.cover)

        val bundle = Bundle().apply {
            putInt("songID", currentSongId)
        }

        val pendingIntent = NavDeepLinkBuilder(context)
            .setComponentName(MainActivity::class.java)
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.songFragment)
            .setArguments(bundle)
            .createPendingIntent()

        val updBuilder = builder
            .setContentTitle(song.title)
            .setContentText(song.author)
            .setLargeIcon(icon)
            .setContentIntent(pendingIntent)
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle())
            .setShowWhen(false)
            .setAutoCancel(false)
            .setSilent(true)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        builder = updBuilder

        manager.notify(1, updBuilder.build())
    }

    @SuppressLint("RestrictedApi")
    fun setState(state: String) {
        if (state == "PLAY") {
            builder.mActions[1] =
                NotificationCompat.Action(R.drawable.ic_baseline_paused, "RESUME", resumeIntent)
        } else if (state == "PAUSE") {
            builder.mActions[1] =
                NotificationCompat.Action(R.drawable.ic_baseline_play, "RESUME", resumeIntent)
        }
        manager.notify(1, builder.build())
    }

    fun cancelNotification() {
        manager.cancelAll()
    }
}
