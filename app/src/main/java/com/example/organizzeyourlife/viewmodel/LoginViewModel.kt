package com.example.organizzeyourlife.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.organizzeyourlife.api.Endpoint
import com.example.organizzeyourlife.api.RetrofitUtils
import com.example.organizzeyourlife.model.User
import com.example.organizzeyourlife.model.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    var loginStatus = MutableLiveData<Boolean?>()
    var loginInfo = MutableLiveData<User>()

    fun loginUser(user: String, password: String) {
        Thread{
            val retrofit =
                RetrofitUtils.getRetrofitInstance("https://organizze-your-life.herokuapp.com/api/")

            val endpoint = retrofit.create(Endpoint::class.java)
            val callBack = endpoint.postLogin(user, password)

            callBack.enqueue(object : Callback<Users>{
                override fun onResponse(call: Call<Users>, response: Response<Users>) {
                    Log.e("Sucesso", "Busca realizada com sucesso")
                    if(response.body() != null){
                        loginInfo.value = response.body()!!.user
                        loginStatus.value = true
                    }else{
                        loginStatus.value = false
                    }
                }

                override fun onFailure(call: Call<Users>, t: Throwable) {
                    Log.e("Erro", "$t")
                    loginStatus.value = false
                }
            })
        }.start()
    }
}