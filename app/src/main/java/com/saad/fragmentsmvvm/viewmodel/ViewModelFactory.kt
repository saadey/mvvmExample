package com.saad.fragmentsmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saad.fragmentsmvvm.repository.MemesRepository

class ViewModelFactory(private val memesRepository: MemesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MemesViewModel(memesRepository) as T
    }

}