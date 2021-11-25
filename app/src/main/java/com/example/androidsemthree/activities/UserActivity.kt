package com.example.androidsemthree.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidsemthree.databinding.ActivityUserBinding

class UserActivity: AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
    }
}
