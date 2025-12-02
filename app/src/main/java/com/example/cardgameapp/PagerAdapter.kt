package com.example.cardgameapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter (activity: FragmentActivity) : FragmentStateAdapter(activity) {


    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> HomeFragment()
            1 -> HighscoreFragment()
            else -> HomeFragment()
        }
    }

    override fun getItemCount(): Int = 2
}
