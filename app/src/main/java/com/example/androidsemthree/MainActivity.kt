package com.example.androidsemthree

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user1 = User("12@gmail.com", "123")
        val editor1 = Editor("myemail@mail.ru", "741")
        val writer1 = Writer("sky43@yandex.ru", "98713")

        editor1.editText()
        editor1.sendChanges()
        editor1.deleteTextDefault()
        writer1.addText()
        writer1.sendText()
        writer1.applyChangesDefault()
    }
}
