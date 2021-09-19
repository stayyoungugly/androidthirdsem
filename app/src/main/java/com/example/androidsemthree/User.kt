package com.example.androidsemthree

import android.util.Log

open class User(
    var email: String,
    var password: String,
) {
    init {
        Log.e("pass", password)
    }
}
