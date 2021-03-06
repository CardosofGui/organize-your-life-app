package com.example.organizzeyourlife.presenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.organizzeyourlife.databinding.ActivityRegisterBinding
import com.example.organizzeyourlife.framework.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        initClick()
        initDados()
    }


    private fun initDados() {
        registerViewModel.statusRegister.observe(this, Observer{
            if(it == true){
                Toast.makeText(this, "Usuario cadastrado com sucesso!", Toast.LENGTH_LONG).show()
                finish()
                startActivity(Intent(this, LoginActivity::class.java))
            }else if(it == false) {
                Toast.makeText(this, "Usuario já cadastrado!", Toast.LENGTH_LONG).show()
            }
        })
    }
    private fun initClick() {
        binding.btnCadastrar.setOnClickListener {
            if(binding.edtUsuarioCadastro.text.toString().isEmpty() || binding.edtSenhaCadastro.text.toString().isEmpty()) {
                Toast.makeText(this, "É necessario preencher todos os campos!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val user = binding.edtUsuarioCadastro.text.toString()
            val password = binding.edtSenhaCadastro.text.toString()

            registerViewModel.registerUser(user, password)
        }
        binding.txtLoginActivity.setOnClickListener {
            finish()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

}