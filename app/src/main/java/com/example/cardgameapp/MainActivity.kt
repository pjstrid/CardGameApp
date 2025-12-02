package com.example.cardgameapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.cardgameapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val adapter = PagerAdapter(this)

        binding.viewPager.adapter = adapter
        binding.viewPager.offscreenPageLimit = adapter.itemCount

        TabLayoutMediator(binding.bottomNavigation, binding.viewPager) {tab, position ->
            tab.text = when(position) {
                0 -> "HOME"
                1 -> "HIGHSCORE"
                else -> ""
            }
        }.attach()

    }
}