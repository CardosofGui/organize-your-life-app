package com.example.organizzeyourlife.framework.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.organizzeyourlife.framework.api.Endpoint
import com.example.organizzeyourlife.framework.api.RetrofitUtils
import com.example.organizzeyourlife.data.TaskListData.findById
import com.example.organizzeyourlife.extensions.formatStringDatePost
import com.example.organizzeyourlife.domain.Task
import com.example.organizzeyourlife.data.TaskActions
import com.example.organizzeyourlife.data.TaskDataRepository
import com.example.organizzeyourlife.domain.TaskInfo
import com.example.organizzeyourlife.domain.singleton.UserSingleton
import com.example.organizzeyourlife.implementation.TaskImplementations
import com.example.organizzeyourlife.usecase.TaskUseCase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskViewModel : ViewModel() {

    private var _statusNewTask = MutableLiveData<Boolean>()
        val statusNewTask : LiveData<Boolean>
        get() = _statusNewTask

    private var _listTask = MutableLiveData<Task>()
        val listTask : LiveData<Task>
        get() = _listTask

    private var _statusDeleteTask = MutableLiveData<Boolean>()
        val statusDeleteTask : LiveData<Boolean>
        get() = _statusNewTask


    private val taskImplementation = TaskImplementations()
    private val taskDataRepository = TaskDataRepository(taskImplementation)
    private val taskUseCase = TaskUseCase(taskDataRepository)


    fun getAllTask(){
        Thread{
            _listTask.postValue(taskUseCase.getAllTaskImplementation())
        }.start()
    }
    fun deleteTask(index : Int){
        Thread{
            _statusNewTask.postValue(taskUseCase.deleteTaskImplementation(index))
        }.start()
    }
    fun updateTask(task : TaskInfo){
        Thread{
            _statusNewTask.postValue(taskUseCase.updateTaskImplementation(task))
        }.start()
    }
    fun insertTask(task : TaskInfo){
        Thread{
            _statusNewTask.postValue(taskUseCase.insertTaskImplementation(task))
        }.start()
    }
}