package com.example.androidsemthree.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidsemthree.databinding.ActivityMessageBinding
import com.google.android.material.snackbar.Snackbar

class MessageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMessageBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        setMessage(intent)
    }

    private fun setMessage(intent: Intent?) {
        val text = intent?.extras?.getString(Intent.EXTRA_TEXT).orEmpty()
        intent?.extras?.run {
            val start = binding.etMessage.selectionStart
            binding.etMessage.text?.insert(start, text)
        }
        with(binding) {
            btSend.setOnClickListener() {
                val warning = "Сообщение отправлено"
                etMessage.text?.clear()
                Snackbar.make(
                    binding.root,
                    warning,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
        setResult(Activity.RESULT_OK)
    }
}
