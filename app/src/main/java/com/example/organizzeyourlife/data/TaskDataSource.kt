package com.example.organizzeyourlife.data

import com.example.organizzeyourlife.domain.Task
import com.example.organizzeyourlife.domain.TaskInfo


interface TaskDataSource {
    fun getAllTask() : Task?
    fun updateTaskApi(task: TaskInfo) : Boolean
    fun deleteTaskApi(index: Int) : Boolean
    fun insertTaskApi(task: TaskInfo) : Boolean
    fun tryConnectionApi() : Boolean
}