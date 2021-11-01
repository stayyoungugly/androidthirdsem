package com.example.androidsemthree

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller = (supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment)
            .navController
    }
}
