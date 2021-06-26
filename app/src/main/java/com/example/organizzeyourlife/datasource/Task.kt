package com.example.organizzeyourlife.datasource

import com.example.organizzeyourlife.model.TaskActions
import com.example.organizzeyourlife.model.TaskInfo

object Task {

    private var listTask = ArrayList<TaskInfo>()
    var taskActions : TaskActions? = null

    fun setList(listTask : ArrayList<TaskInfo>){
        this.listTask = listTask
    }

    fun getList() = this.listTask

    fun findById(index : Int) = this.listTask[index]

    fun deleteTask(index: Int){
        taskActions?.deleteTaskApi(index)
        listTask.removeAt(index)
    }

    fun addTask(task : TaskInfo){

        if(task.idTask == null){
            listTask.add(task)
            taskActions?.insertTaskApi(task)
        }else{
            listTask.remove(task)
            listTask.add(task)
            taskActions?.updateTaskApi(task)
        }

    }
}