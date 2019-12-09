package com.georgcantor.newsproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.georgcantor.newsproject.repository.INewsRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repository: INewsRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }

}