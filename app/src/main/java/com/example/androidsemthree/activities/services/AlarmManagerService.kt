package com.example.androidsemthree.activities.services

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import com.example.androidsemthree.activities.receivers.AlarmReceiver
import com.example.androidsemthree.activities.receivers.BootReceiver
import java.util.*

class AlarmManagerService(context: Context) {
    companion object {
        const val REQUEST_CODE_1 = 1
    }

    private lateinit var intent: Intent
    private lateinit var pendingIntent: PendingIntent
    private lateinit var service: NotificationService
    private lateinit var calendar: Calendar

    private val alarmManager by lazy {
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

    init {
        val receiver = ComponentName(context, BootReceiver::class.java)
        context.packageManager.setComponentEnabledSetting(
            receiver,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    fun setAlarm(context: Context, hour: String, minute: String) {
        intent = Intent(context, AlarmReceiver::class.java).let {
            it.putExtra("HOUR_STRING", hour)
            it.putExtra("MINUTE_STRING", minute)
        }
        pendingIntent = PendingIntent.getBroadcast(
            context,
            REQUEST_CODE_1,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        setCalendar(hour, minute)
        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    }

    private fun setCalendar(hour: String, minute: String) {
        calendar = Calendar.getInstance()
        calendar.time = Date(System.currentTimeMillis())
        calendar.timeInMillis = System.currentTimeMillis()

        calendar[Calendar.HOUR] = hour.toInt()
        calendar[Calendar.MINUTE] = minute.toInt()
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0

        checkTime()
    }

    private fun checkTime() {
        val now = Calendar.getInstance()
        now.time = Date(System.currentTimeMillis())
        now.timeInMillis = System.currentTimeMillis()
        if (now.timeInMillis > calendar.timeInMillis) {
            calendar[Calendar.DAY_OF_MONTH] += 1
        }
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    fun cancelAlarm(context: Context) {
        intent = Intent(context, AlarmReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(
            context,
            REQUEST_CODE_1,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        alarmManager.cancel(pendingIntent)
        service = NotificationService(context)
        service.cancelNotification()
    }
}
