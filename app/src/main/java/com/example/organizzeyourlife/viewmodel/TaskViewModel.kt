package com.example.organizzeyourlife.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.organizzeyourlife.api.Endpoint
import com.example.organizzeyourlife.api.RetrofitUtils
import com.example.organizzeyourlife.datasource.Task.findById
import com.example.organizzeyourlife.extensions.formatStringDatePost
import com.example.organizzeyourlife.model.Task
import com.example.organizzeyourlife.model.TaskActions
import com.example.organizzeyourlife.model.TaskInfo
import com.example.organizzeyourlife.model.singleton.UserSingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskViewModel : ViewModel(), TaskActions {

    private var _statusNewTask = MutableLiveData<Boolean>()
        val statusNewTask
        get() = _statusNewTask

    private var _listTask = MutableLiveData<Task>()
        val listTask
        get() = _listTask

    fun init(){
        com.example.organizzeyourlife.datasource.Task.taskActions = this
    }
    fun getAllTasks(){
        val retrofit =
            RetrofitUtils.getRetrofitInstance("https://organizze-your-life.herokuapp.com/api/")
        val endpoint = retrofit.create(Endpoint::class.java)
        val callback = endpoint.getAllTask(UserSingleton.user!!.idUser)

        Thread{
            callback.enqueue(object : Callback<Task> {
                override fun onResponse(call: Call<Task>, response: Response<Task>) {
                    Log.e("Sucesso", "$response")
                    _listTask.value = response.body()
                }

                override fun onFailure(call: Call<Task>, t: Throwable) {
                    Log.e("Erro", "$t")
                }
            })
        }.start()
    }
    override fun updateTaskApi(task: TaskInfo) {
        val retrofit = RetrofitUtils.getRetrofitInstance("https://organizze-your-life.herokuapp.com/api/")
        val endpoint = retrofit.create(Endpoint::class.java)
        val callback = endpoint.updateTask(task.task, task.description, task.date!!.formatStringDatePost(), task.time!!, task.idTask!!, task.idUser)

        Log.e("Teste", "${task.task}, ${task.description}, ${task.date!!.formatStringDatePost()}, ${task.time!!}, ${task.idTask!!}, ${task.idUser}")
        Thread{
            callback.enqueue(object : Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    Log.e("Sucesso", response.body().toString())
                    statusNewTask.value = true
                }
                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.e("Erro", t.toString())
                    statusNewTask.value = false
                }
            })
        }.start()
    }
    override fun deleteTaskApi(index: Int) {
        val task = findById(index)

        val retrofit =
            RetrofitUtils.getRetrofitInstance("https://organizze-your-life.herokuapp.com/api/")
        val endpoint = retrofit.create(Endpoint::class.java)
        val callback = endpoint.deleteTask(task.idTask!!, UserSingleton.user!!.idUser)


        callback.enqueue(object : Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                Log.e("Sucesso", response.body().toString())
            }
            override fun onFailure(call: Call<Int>, t: Throwable) {
                Log.e("Erro", t.toString())
            }
        })
    }
    override fun insertTaskApi(task: com.example.organizzeyourlife.model.TaskInfo) {
        val retrofit = RetrofitUtils.getRetrofitInstance("https://organizze-your-life.herokuapp.com/api/")
        val endpoint = retrofit.create(Endpoint::class.java)
        val callback = endpoint.postTask(task.task, task.description, task.date!!.formatStringDatePost(), task.time!!, task.idUser)

        Thread{
            callback.enqueue(object : Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    Log.e("Sucesso", response.body().toString())
                    statusNewTask.value = true
                }
                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.e("Erro", t.toString())
                    statusNewTask.value = false
                }
            })
        }.start()
    }
}