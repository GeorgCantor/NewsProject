package com.georgcantor.newsproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class BaseViewModel : ViewModel() {

    private val progressLiveData = MutableLiveData<Boolean>()

    val isProgressShow: MutableLiveData<Boolean>
        get() = progressLiveData

    protected fun <T : Any> loadData(
        block: suspend (() -> T?),
        callback: ((T?) -> Unit)
    ): Job {
        return viewModelScope.launch(Dispatchers.Main) {
            progressLiveData.value = true

            val data = withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
                block()
            }
            callback(data)

            progressLiveData.value = false
        }
    }

}