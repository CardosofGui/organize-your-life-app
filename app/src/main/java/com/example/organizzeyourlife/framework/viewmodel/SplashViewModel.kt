package com.example.organizzeyourlife.framework.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.organizzeyourlife.data.TaskDataRepository
import com.example.organizzeyourlife.framework.api.Endpoint
import com.example.organizzeyourlife.framework.api.RetrofitUtils
import com.example.organizzeyourlife.implementation.TaskImplementations
import com.example.organizzeyourlife.usecase.TaskUseCase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashViewModel : ViewModel() {

    private val taskImplementations = TaskImplementations()
    private val taskDataRepository = TaskDataRepository(taskImplementations)
    private val taskUseCase = TaskUseCase(taskDataRepository)

    private var _statusConnection = MutableLiveData<Boolean>()
        val statusConnection
        get() = _statusConnection

    fun tryConnectionApi(){
        Thread{
            _statusConnection.postValue(taskUseCase.tryConnectionApi())
        }.start()
    }
}