package com.ashwin.coroutinesmockk

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers

/**
 * This factory is required so that we can mock CoroutineContext by overriding CoroutineContextProvider in our unit tests.
 */
class TestableViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TestableViewModel(Dispatchers.IO) as T
    }
}

class NetworkViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NetworkViewModel(Dispatchers.IO) as T
    }
}
