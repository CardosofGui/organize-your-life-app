package com.example.organizzeyourlife.domain

class Users(
    val status : String,
    val user : User
)

class User(
    val idUser : String,
    val username : String,
    val password : String
)
