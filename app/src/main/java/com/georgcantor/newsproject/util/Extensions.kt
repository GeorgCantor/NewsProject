package com.georgcantor.newsproject.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.georgcantor.newsproject.R

fun AppCompatActivity.openFragment(fragment: Fragment) {
    val transaction = supportFragmentManager.beginTransaction()

    transaction.replace(R.id.frameContainer, fragment)
    transaction.addToBackStack(null)
    transaction.commit()

}