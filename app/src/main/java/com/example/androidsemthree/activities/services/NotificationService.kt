package com.example.androidsemthree.activities.services

import android.app.Notification.*
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.annotation.RawRes
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.VISIBILITY_PUBLIC
import com.example.androidsemthree.R
import com.example.androidsemthree.activities.UserActivity
import java.io.File


class NotificationService(context: Context) {
    companion object {
        const val NOTIFICATION_ID = 1
        const val CHANNEL_ID = "channel_id_1"
        const val REQUEST_CODE_1 = 1
    }

    private val manager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    private val audio by lazy {
        AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()
    }

    private val pattern = arrayOf(100L, 200L, 0, 400L).toLongArray()

    fun showNotification(context: Context, title: String) {

        val sound: Uri = context.getSoundUri(R.raw.song_alarm)

        val moveIntent = Intent(context, UserActivity::class.java).let {
            PendingIntent.getActivities(
                    context,
                    REQUEST_CODE_1,
                    arrayOf(it),
                    PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_access_alarm_24)
                .setContentTitle("It is $title already")
                .setShowWhen(true)
                .setAutoCancel(true)
                .setContentIntent(moveIntent)
                .setContentText("Wake Up!")
                .setCategory(CATEGORY_ALARM)
                .setOngoing(true)

        notificationChannelCreator(context, sound, builder)

        val notification = builder.build()

        notification.flags = notification.flags + FLAG_INSISTENT

        manager.notify(NOTIFICATION_ID, notification)
    }

    private fun notificationChannelCreator(
            context: Context,
            sound: Uri,
            builder: NotificationCompat.Builder
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                    CHANNEL_ID,
                    context.getString(R.string.channel_title),
                    IMPORTANCE_HIGH
            ).apply {
                description = context.getString(R.string.channel_description)
                lightColor = Color.BLUE
                vibrationPattern = pattern
                setSound(sound, audio)
                lockscreenVisibility = VISIBILITY_PUBLIC
            }.also {
                manager.createNotificationChannel(it)
            }
        } else {
            with(builder) {
                color = Color.BLUE
                setVibrate(pattern)
                setSound(sound)
                setVisibility(VISIBILITY_PUBLIC)
            }
        }
    }

    fun cancelNotification() {
        manager.cancelAll()
    }
    private fun Context.getSoundUri(
            @RawRes id: Int
    ) = Uri.parse("android.resource://${packageName}/$id")
}
