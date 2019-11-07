package com.georgcantor.newsproject.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.georgcantor.newsproject.R
import com.georgcantor.newsproject.util.openFragment
import com.georgcantor.newsproject.view.fragment.NewsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openFragment(NewsFragment())
    }

}
