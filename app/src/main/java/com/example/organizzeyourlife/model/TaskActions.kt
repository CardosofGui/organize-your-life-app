package com.example.organizzeyourlife.model


interface TaskActions {
    fun updateTaskApi(task: TaskInfo)
    fun deleteTaskApi(index: Int)
    fun insertTaskApi(task: TaskInfo)
}