package com.example.organizzeyourlife.data

import com.example.organizzeyourlife.domain.TaskInfo
import com.example.organizzeyourlife.implementation.TaskImplementations

object TaskListData {

    private var listTask = ArrayList<TaskInfo>()

    fun setList(listTask : ArrayList<TaskInfo>){
        TaskListData.listTask = listTask
    }

    fun getList() = listTask

    fun findById(index : Int) = listTask[index]


    fun deleteTask(index: Int){
        listTask.removeAt(index)
    }

    fun addTask(task : TaskInfo){
        if(task.idTask == null){
            listTask.add(task)
        }else{
            listTask.remove(task)
            listTask.add(task)
        }
    }
}