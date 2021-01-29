package com.ashwin.coroutinesmockk

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class TestableViewModel(ioDispacher : CoroutineDispatcher): ViewModel() {
    val ioScope = CoroutineScope(ioDispacher)

    val _count: MutableLiveData<Int> = MutableLiveData<Int>(0)
    val count: LiveData<Int> = _count

    fun addOne() {
        ioScope.launch {
            delay(5000)
            _count.postValue(_count.value?.plus(1))
        }
    }

    fun subtractOne() {
        ioScope.launch {
            val result = subtract(_count.value, 1)
            _count.postValue(result)
        }
    }

    suspend fun subtract(n: Int?, i: Int?): Int? {
        delay(5000)
        return n?.minus(i ?: 0)
    }
}