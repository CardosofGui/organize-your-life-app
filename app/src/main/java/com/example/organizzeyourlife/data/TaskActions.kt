package com.example.organizzeyourlife.data

import com.example.organizzeyourlife.domain.Task
import com.example.organizzeyourlife.domain.TaskInfo


interface TaskActions {
    fun getAllTask() : Task?
    fun updateTaskApi(task: TaskInfo) : Boolean
    fun deleteTaskApi(index: Int) : Boolean
    fun insertTaskApi(task: TaskInfo) : Boolean
}