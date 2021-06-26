package com.example.organizzeyourlife.model

import java.util.*
import kotlin.collections.ArrayList

data class Task(
    val status : String,
    var taskInfo : ArrayList<TaskInfo>
)

class TaskInfo (
    val idTask : Int?,
    val task : String,
    val description : String,
    val date : String?,
    val time : String?,
    val idUser : String
){
    override fun hashCode(): Int {
        return idTask!!
    }
}
