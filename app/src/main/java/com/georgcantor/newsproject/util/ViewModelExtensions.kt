package com.georgcantor.newsproject.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.georgcantor.newsproject.view.DaggerActivity

inline fun <reified T : ViewModel> DaggerActivity.getViewModel(): T {
    return ViewModelProviders.of(this, viewModelFactory).get(T::class.java)
}

inline fun <reified T : ViewModel> Fragment.getViewModel(): T {
    return ViewModelProviders.of(this).get(T::class.java)
}
