package com.example.androidsemthree.activities.services

import android.content.Context
import java.io.File

class FileService(context: Context) {

    private var alarmFile: File

    init {
        alarmFile = File(File(context.filesDir, "FILES"), "alarm.txt")
    }

    fun createFile(context: Context, fileName: String): File {
        val path = context.filesDir
        val letDirectory = File(path, "FILES")
        letDirectory.mkdirs()
        alarmFile = File(letDirectory, fileName)
        return alarmFile
    }

    fun appendFile(context: Context, hour: String, minute: String) {
        if (!alarmFile.exists()) {
            createFile(context, "alarm.txt")
        }
        alarmFile.appendText(hour + "\n")
        alarmFile.appendText(minute)
    }

    fun clearFile() {
        alarmFile.delete()
    }

    fun getFile(): File {
        return alarmFile
    }
}
