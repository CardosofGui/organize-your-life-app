package com.example.organizzeyourlife.data

import com.example.organizzeyourlife.domain.TaskInfo
import com.example.organizzeyourlife.implementation.TaskImplementations

class TaskDataRepository(private val taskImplementations: TaskImplementations) {

    fun getAllTasks() = taskImplementations.getAllTask()
    fun insertTask(task : TaskInfo) = taskImplementations.insertTaskApi(task)
    fun updateTask(task : TaskInfo) = taskImplementations.updateTaskApi(task)
    fun deleteTask(index : Int) = taskImplementations.deleteTaskApi(index)
    fun tryConnectionApi() = taskImplementations.tryConnectionApi()

}