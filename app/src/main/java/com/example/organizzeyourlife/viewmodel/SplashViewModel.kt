package com.example.organizzeyourlife.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.organizzeyourlife.api.Endpoint
import com.example.organizzeyourlife.api.RetrofitUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashViewModel : ViewModel() {

    private var _statusConnection = MutableLiveData<Boolean>()
        val statusConnection
        get() = _statusConnection

    fun tryConnectionApi(){
        val retrofit =
            RetrofitUtils.getRetrofitInstance("https://organizze-your-life.herokuapp.com/")
        val endpoint = retrofit.create(Endpoint::class.java)
        val callback = endpoint.tryConnection()


        Thread{
            callback.enqueue(object : Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    _statusConnection.postValue(true)
                }
                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.e("erro", "$t")
                    _statusConnection.postValue(false)
                }
            })
        }.start()
    }
}