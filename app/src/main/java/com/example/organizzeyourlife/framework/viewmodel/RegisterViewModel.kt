package com.example.organizzeyourlife.framework.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.organizzeyourlife.framework.api.Endpoint
import com.example.organizzeyourlife.framework.api.RetrofitUtils
import retrofit2.Call
import retrofit2.Response

class RegisterViewModel : ViewModel() {

    var statusRegister : MutableLiveData<Boolean?> = MutableLiveData<Boolean?>()

    fun registerUser(usuario : String, senha : String){
        val retrofit = RetrofitUtils.getRetrofitInstance("https://organizze-your-life.herokuapp.com/api/")
        val endpoint = retrofit.create(Endpoint::class.java)
        val callback = endpoint.postUser(usuario, senha)

        Thread{
            callback.enqueue(object : retrofit2.Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    Log.e("Sucesso", response.body().toString())
                    statusRegister.value = response.body() != null
                }
                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.e("Erro", t.toString())
                    statusRegister.value = false
                }
            })
        }.start()
    }
}