package com.saad.fragmentsmvvm.repository

import androidx.lifecycle.MutableLiveData
import com.saad.fragmentsmvvm.model.Jokes
import com.saad.fragmentsmvvm.api.ApiInterface

class MemesRepository(private val apiInterface: ApiInterface) {
    private val memesLiveData = MutableLiveData<Jokes>()
    val liveData
    get() = memesLiveData

    suspend fun getMemes(){
        val result = apiInterface.getMemes()
        memesLiveData.postValue(result.body())
    }

}