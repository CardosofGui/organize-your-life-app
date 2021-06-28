package com.example.organizzeyourlife.framework.api

import com.example.organizzeyourlife.domain.Task
import com.example.organizzeyourlife.domain.Users
import retrofit2.Call
import retrofit2.http.*

interface Endpoint {

    @GET("user/{id}/task")
    fun getAllTask(@Path("id") id: String) : Call<Task>

    @POST("user/register")
    @FormUrlEncoded
    fun postUser(@Field("username") user : String,
                 @Field("password") pass : String) : Call<Int>

    @POST("user/login")
    @FormUrlEncoded
    fun postLogin(@Field("username") user : String,
    @Field("password") pass : String) : Call<Users>

    @POST("user/inserirTask")
    @FormUrlEncoded
    fun postTask(
        @Field("nomeTask") nomeTask : String,
        @Field("descricaoTask") descTask : String,
        @Field("dataTask") dataTask: String,
        @Field("horaTask") horaTask: String,
        @Field("idUser") idUser : String
    ) : Call<Int>

    @POST("user/deleteTask")
    @FormUrlEncoded
    fun deleteTask(
        @Field("idTask") idTask : Int,
        @Field("idUser") idUser : String
    ) : Call<Int>

    @POST("user/updateTask")
    @FormUrlEncoded
    fun updateTask(
        @Field("nomeTask") nomeTask : String,
        @Field("descricaoTask") descTask : String,
        @Field("dataTask") dataTask: String,
        @Field("horaTask") horaTask: String,
        @Field("idTask") idTask : Int,
        @Field("idUser") idUser : String
    ) : Call<Int>

    @GET("user/tryConnection")
    fun tryConnection() : Call<Int?>
}