package com.example.androidsemthree.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.androidsemthree.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller = (supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment)
            .navController

        val bnvMain = findViewById<BottomNavigationView>(R.id.bnv_main)
        bnvMain.setupWithNavController(controller)

        bnvMain.setOnItemReselectedListener {}
    }

    override fun onSupportNavigateUp(): Boolean = controller.navigateUp()
}
