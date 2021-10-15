package com.example.androidsemthree.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.*
import androidx.core.content.ContextCompat
import com.example.androidsemthree.R
import com.example.androidsemthree.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

private const val REQUEST_CODE_1 = 1
private var pressed = false

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        window.statusBarColor = ContextCompat.getColor(this, R.color.start_brown)

        binding.editButton.setOnClickListener {
            editName()
        }

        binding.menuButton.setOnClickListener {
            shareProfileLink()
        }
    }

    private fun editName() {
        if (!pressed) {
            with(binding) {
                tvName.visibility = INVISIBLE
                etName.visibility = VISIBLE
                editButton.text = "Сохранить"
            }
            pressed = true
        } else {
            with(binding) {
                etName.visibility = GONE
                tvName.text = binding.etName.text
                editButton.text = "Изменить имя"
                tvName.visibility = VISIBLE
            }
            pressed = false
        }
    }

    private fun shareProfileLink() {
        val name = binding.tvName.text
        val link = name.hashCode()
        val fullLink = "https://open.spotify.com/user/${link}"
        val message = "Привет! Вот ссылка на мой профиль ($name): $fullLink"
        sendIntent(message)
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun sendIntent(message: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, message)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_CODE_1)
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        if (resultCode != Activity.RESULT_OK) {
            super.onActivityResult(requestCode, resultCode, data)
            return
        }
        when (requestCode) {
            REQUEST_CODE_1 -> {
                val warning = "Сообщение было успешно отправлено"
                Snackbar.make(
                    binding.root,
                    warning,
                    Snackbar.LENGTH_LONG
                ).show()
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
