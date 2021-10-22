package com.example.androidsemthree

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidsemthree.databinding.ActivityMainBinding
import com.example.androidsemthree.fragments.AddFragment
import com.example.androidsemthree.fragments.FavouritesFragment
import com.example.androidsemthree.fragments.HomeFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        var flag = true

        with(binding) {
            btHome.setOnClickListener {
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.animator.slight_in_left, R.animator.slide_in_right)
                    .replace(R.id.one, HomeFragment())
                    .addToBackStack(null)
                    .commit()
                if (flag) {
                    tvMenu.text = null
                    flag = false
                }
            }

            btAdd.setOnClickListener {
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.animator.slight_in_left, R.animator.slide_in_right)
                    .replace(R.id.one, AddFragment())
                    .addToBackStack(null)
                    .commit()
                if (flag) {
                    tvMenu.text = null
                    flag = false
                }
            }

            btFavourites.setOnClickListener {
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.animator.slight_in_left, R.animator.slide_in_right)
                    .replace(R.id.one, FavouritesFragment())
                    .addToBackStack(null)
                    .commit()
                if (flag) {
                    tvMenu.text = null
                    flag = false
                }
            }
        }
    }
}
