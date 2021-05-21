package com.example.kotlin_app.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm){

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> FriendsListFragment().newInstant()
            1 -> ChattingListFragment().newInstant()
            2 -> ProfileFragment().newInstant()
            else -> FriendsListFragment().newInstant()
        }
    }
}