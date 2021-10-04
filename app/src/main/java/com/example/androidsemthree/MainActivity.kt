package com.example.androidsemthree

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor = ContextCompat.getColor(this, R.color.start_brown)

        val editButton: Button = findViewById(R.id.edit_button)
        val nameEt: EditText = findViewById(R.id.et_name)
        val nameTv: TextView = findViewById(R.id.tv_name)

        var pressed = false;
        editButton.setOnClickListener {
            if (!pressed) {
                nameTv.visibility = INVISIBLE
                nameEt.visibility = VISIBLE
                editButton.text = "Сохранить"
                pressed = true
            } else {
                nameEt.visibility = GONE
                nameTv.text = nameEt.text
                editButton.text = "Изменить имя"
                nameTv.visibility = VISIBLE
                pressed = false
            }
        }
    }
}
