package com.example.organizzeyourlife.domain.singleton

import com.example.organizzeyourlife.domain.User

class UserSingleton {
    companion object{
        var user : User? = null

        const val SHARED_PREFERENCES_NAME = "com.example.organnizeyourlife.SHARED_PREFERENCES_MAIN"
        const val SHARED_PREFERENCES_LOGIN = "com.example.organnizeyourlife.SHARED_PREFERENCES_MAIN.LOGIN"
    }
}