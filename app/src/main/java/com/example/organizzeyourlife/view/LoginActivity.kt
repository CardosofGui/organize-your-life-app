package com.example.organizzeyourlife.view

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.organizzeyourlife.databinding.ActivityLoginBinding
import com.example.organizzeyourlife.model.singleton.UserSingleton.Companion.SHARED_PREFERENCES_LOGIN
import com.example.organizzeyourlife.model.singleton.UserSingleton.Companion.SHARED_PREFERENCES_NAME
import com.example.organizzeyourlife.model.singleton.UserSingleton.Companion.user
import com.example.organizzeyourlife.viewmodel.LoginViewModel
import com.google.gson.Gson

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferesEditor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        initClick()
        initDados()
        initSharedPreferences()
    }

    @SuppressLint("CommitPrefEdits")
    private fun initSharedPreferences() {
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE)
        sharedPreferesEditor = sharedPreferences.edit()
    }

    private fun initDados() {
        loginViewModel.loginStatus.observe(this, Observer {
            if(it == true){
                Toast.makeText(this, "Usuario logado com sucesso", Toast.LENGTH_LONG).show()
                showHideLogging()

                sharedPreferesEditor.putString(SHARED_PREFERENCES_LOGIN, Gson().toJson(loginViewModel.loginInfo.value)).commit()
                user = loginViewModel.loginInfo.value

                finish()
                startActivity(Intent(this, MenuActivity::class.java))
            }else if(it == false){
                Toast.makeText(this, "Usuario e/ou senha incorretos", Toast.LENGTH_LONG).show()
                showHideLogging()
            }
        })
    }

    private fun initClick() {
        binding.btnLogin.setOnClickListener {
            if(binding.edtSenhaLogin.text.toString().isEmpty() || binding.edtUsuarioLogin.text.toString().isEmpty()) {
                Toast.makeText(this, "É necessario preencher todos os campos!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val user = binding.edtUsuarioLogin.text.toString()
            val password = binding.edtSenhaLogin.text.toString()

            loginViewModel.loginUser(user, password)
            showHideLogging()
        }

        binding.txtRegisterActivity.setOnClickListener {
            finish()
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun showHideLogging(){
        if(binding.txtLogando.visibility == View.GONE && binding.progLogando.visibility == View.GONE){
            binding.txtLogando.visibility = View.VISIBLE
            binding.progLogando.visibility = View.VISIBLE

            blockInterfaceAction(false)
        }else{
            binding.txtLogando.visibility = View.GONE
            binding.progLogando.visibility = View.GONE

            blockInterfaceAction(true)
        }
    }

    private fun blockInterfaceAction(status : Boolean){
        binding.edtSenhaLogin.isEnabled = status
        binding.edtUsuarioLogin.isEnabled = status
        binding.btnLogin.isEnabled = status
        binding.txtRegisterActivity.isEnabled = status
    }
}