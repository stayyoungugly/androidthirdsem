package com.example.androidsemthree.activities

import com.example.androidsemthree.activities.services.FileService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.example.androidsemthree.R
import com.example.androidsemthree.activities.services.AlarmManagerService
import com.example.androidsemthree.activities.services.NotificationService
import com.example.androidsemthree.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var alarmManagerService: AlarmManagerService? = null
    private var fileService: FileService? = null
    private lateinit var notificationService: NotificationService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        alarmManagerService = AlarmManagerService(this)

        fileService = FileService(this)
        var isStarted = false
        //var file = fileService?.getFile()
//        if (file?.exists() == true) {
//            val time = file.readLines() as ArrayList<String>
//            val hour = time[0]
//            val minute = time[1]
//            with(binding) {
//                etHour.setText(hour)
//                etMin.setText(minute)
//                isStarted = afterStart()
//            }
//        }

        with(binding) {
            notificationService = NotificationService(this@MainActivity)
            btStart.setOnClickListener {
                hideKeyboard()
                val hour = etHour.text.toString()
                val minute = etMin.text.toString()
                if (checkStart(hour, minute)) {
                    // file = fileService?.createFile(this@MainActivity, "alarm.txt")
                    // fileService?.appendFile(this@MainActivity, hour, minute)
                    alarmManagerService?.setAlarm(this@MainActivity, hour, minute)
                    isStarted = afterStart()
                }
                showMessage(isStarted)
            }
            btStop.setOnClickListener {
                if (isStarted) {
                    // fileService?.clearFile()
                    alarmManagerService?.cancelAlarm(this@MainActivity)
                    isStarted = afterStop()
                }
            }
        }
    }

    private fun ActivityMainBinding.afterStop(): Boolean {
        btStart.isClickable = true
        etHour.isEnabled = true
        etMin.isEnabled = true
        tvEnter.text = getString(R.string.enter_time)
        return false
    }

    private fun ActivityMainBinding.afterStart(): Boolean {
        btStart.isClickable = false
        etHour.isEnabled = false
        etMin.isEnabled = false
        tvEnter.text = getString(R.string.current_alarm)
        return true
    }

    private fun hideKeyboard() {
        this@MainActivity.currentFocus?.let { view ->
            val imm = getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun showMessage(flag: Boolean) {

        val message: String = if (flag) {
            "ALARM WAS SET"
        } else {
            "ERROR: VALID TIME"
        }

        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun checkStart(hour: String, minute: String): Boolean {
        val flag: Boolean

        when {
            hour.length > 2 -> return false
            hour.isEmpty() -> return false
            minute.length > 2 -> return false
            minute.isEmpty() -> return false
        }

        val hourInt = hour.toInt()
        val minuteInt = minute.toInt()

        flag = when {
            hourInt > 23 -> false
            minuteInt > 59 -> false
            else -> true
        }
        return flag
    }

    override fun onDestroy() {
        super.onDestroy()
        alarmManagerService = null
    }
}
