package com.example.androidsemthree.activities.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.androidsemthree.activities.services.AlarmManagerService
import com.example.androidsemthree.activities.services.FileService

class BootReceiver : BroadcastReceiver() {
    private lateinit var alarmManagerService: AlarmManagerService
    private lateinit var fileService: FileService

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            alarmManagerService = AlarmManagerService(context)
            fileService = FileService(context)
            val file = fileService.getFile()
            if (file.exists()) {
                val time = file.readLines() as ArrayList<String>
                val hour = time[0]
                val minute = time[1]
                alarmManagerService.setAlarm(context, hour, minute)
            }
        }
    }
}
