package com.example.organizzeyourlife.implementation

import android.util.Log
import com.example.organizzeyourlife.data.TaskDataSource
import com.example.organizzeyourlife.data.TaskListData
import com.example.organizzeyourlife.domain.Task
import com.example.organizzeyourlife.domain.TaskInfo
import com.example.organizzeyourlife.domain.singleton.UserSingleton
import com.example.organizzeyourlife.extensions.formatStringDatePost
import com.example.organizzeyourlife.framework.api.Endpoint
import com.example.organizzeyourlife.framework.api.RetrofitUtils.Companion.getRetrofitInstance

class TaskImplementations : TaskDataSource {

    private val retrofit = getRetrofitInstance("https://organizze-your-life.herokuapp.com/api/")
    private var listTask : Task? = null

    override fun getAllTask() : Task? {
        val endpoint = retrofit.create(Endpoint::class.java)
        val callback = endpoint.getAllTask(UserSingleton.user!!.idUser).execute()


        listTask = if(callback.isSuccessful){
            callback.body()!!
        }else{
            null
        }

        if(listTask != null){
            TaskListData.setList(listTask!!.taskInfo)
        }else{
            TaskListData.setList(arrayListOf())
        }

        return listTask
    }
    override fun updateTaskApi(task: TaskInfo) : Boolean {
        val endpoint = retrofit.create(Endpoint::class.java)
        val callback = endpoint.updateTask(task.task, task.description, task.date!!.formatStringDatePost(), task.time!!, task.idTask!!, task.idUser).execute()

        Log.e("Teste", "${task.task}, ${task.description}, ${task.date.formatStringDatePost()}, ${task.time}, ${task.idTask!!}, ${task.idUser}")

        TaskListData.addTask(task)
        return callback.isSuccessful
    }
    override fun deleteTaskApi(index: Int) : Boolean {
        val task = TaskListData.findById(index)

        val endpoint = retrofit.create(Endpoint::class.java)
        val callback = endpoint.deleteTask(task.idTask!!, UserSingleton.user!!.idUser).execute()

        TaskListData.deleteTask(index)
        return callback.isSuccessful
    }
    override fun insertTaskApi(task: TaskInfo) : Boolean {
        val endpoint = retrofit.create(Endpoint::class.java)
        val callback = endpoint.postTask(task.task, task.description, task.date!!.formatStringDatePost(), task.time!!, task.idUser).execute()

        TaskListData.addTask(task)
        return callback.isSuccessful
    }

    override fun tryConnectionApi(): Boolean {
        val endpoint = retrofit.create(Endpoint::class.java)
        val callback = endpoint.tryConnection().execute()

        return callback.isSuccessful
    }
}