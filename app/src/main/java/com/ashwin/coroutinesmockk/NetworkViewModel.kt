package com.ashwin.coroutinesmockk

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class NetworkViewModel(ioDispacher : CoroutineDispatcher): ViewModel() {
    val ioScope = CoroutineScope(ioDispacher)

    val URL = "https://jsonplaceholder.typicode.com/users/1"

    private val _response = MutableLiveData<String>("")
    val response: LiveData<String> = _response

    fun requestResponse() {
        ioScope.launch {
            val res = getResponse(URL)
            _response.postValue(res)
        }
    }
}
