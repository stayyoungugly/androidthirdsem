package com.example.androidsemthree.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.androidsemthree.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private var controller: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller = (supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment)
            .navController
    }

    override fun onDestroy() {
        super.onDestroy()
        controller = null
    }

}
