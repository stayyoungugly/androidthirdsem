package com.example.androidsemthree.activities.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.androidsemthree.activities.services.NotificationService

class AlarmReceiver: BroadcastReceiver() {
    private var service: NotificationService? = null

    override fun onReceive(context: Context, intent: Intent?) {
            val hour = intent?.extras?.getString("HOUR_STRING")
            val minute = intent?.extras?.getString("MINUTE_STRING")
            service = NotificationService(context)
            service?.showNotification(context, "$hour:$minute")

    }
}

