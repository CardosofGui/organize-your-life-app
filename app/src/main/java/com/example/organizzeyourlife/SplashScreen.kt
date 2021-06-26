package com.example.organizzeyourlife

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.organizzeyourlife.api.Endpoint
import com.example.organizzeyourlife.api.RetrofitUtils.Companion.getRetrofitInstance
import com.example.organizzeyourlife.databinding.ActivitySplashScreenBinding
import com.example.organizzeyourlife.model.User
import com.example.organizzeyourlife.model.singleton.UserSingleton
import com.example.organizzeyourlife.view.MenuActivity
import com.example.organizzeyourlife.view.RegisterActivity
import com.example.organizzeyourlife.viewmodel.SplashViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashScreen : AppCompatActivity() {

    private lateinit var binding : ActivitySplashScreenBinding
    private lateinit var splashViewModel : SplashViewModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        splashViewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        splashViewModel.tryConnectionApi()
        initObserver()
    }

    private fun initObserver() {
        splashViewModel.statusConnection.observe(this, {
            if(it == true){
                if(checkLogin()){
                    startActivity(Intent(this, MenuActivity::class.java))
                    finish()
                }else{
                    startActivity(Intent(this, RegisterActivity::class.java))
                    finish()
                }
            }else if(it == false){
                showConnectionError()
            }
        })
    }

    private fun showConnectionError(){
        binding.llnLoadingApi.visibility = View.GONE
        binding.llnConnectionFail.visibility = View.VISIBLE
    }

    @SuppressLint("CommitPrefEdits")
    private fun initSharedPreferences() {
        sharedPreferences = getSharedPreferences(UserSingleton.SHARED_PREFERENCES_NAME, MODE_PRIVATE)
    }

    private fun checkLogin() : Boolean{
        initSharedPreferences()

        val User : User? = Gson().fromJson<User>(sharedPreferences.getString(UserSingleton.SHARED_PREFERENCES_LOGIN, ""), object : TypeToken<User>(){}.type)

        if(User != null){
            UserSingleton.user = User
            return  true
        }

        return false
    }
}