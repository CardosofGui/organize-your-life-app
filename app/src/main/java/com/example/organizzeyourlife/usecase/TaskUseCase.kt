package com.example.organizzeyourlife.usecase

import com.example.organizzeyourlife.data.TaskDataRepository
import com.example.organizzeyourlife.domain.TaskInfo

class TaskUseCase(private val taskDataRepository: TaskDataRepository) {

    fun getAllTaskImplementation() = taskDataRepository.getAllTasks()
    fun insertTaskImplementation(task : TaskInfo) = taskDataRepository.insertTask(task)
    fun updateTaskImplementation(task : TaskInfo) = taskDataRepository.updateTask(task)
    fun deleteTaskImplementation(index : Int) = taskDataRepository.deleteTask(index)
}