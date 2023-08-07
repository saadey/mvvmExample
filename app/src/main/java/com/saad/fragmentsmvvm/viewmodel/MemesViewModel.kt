package com.saad.fragmentsmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saad.fragmentsmvvm.repository.MemesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemesViewModel (val memesRepository: MemesRepository) : ViewModel(){

    init {
        viewModelScope.launch (Dispatchers.IO) {
            memesRepository.getMemes()
        }
    }

    val memes
    get() = memesRepository.liveData


}