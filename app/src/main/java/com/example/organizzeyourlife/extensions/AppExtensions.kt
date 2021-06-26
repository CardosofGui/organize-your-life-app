package com.example.organizzeyourlife.extensions

import java.util.*

fun String.formatStringDatePost() : String {
    val dataArray = this.split("/")
    return "${dataArray[2]}-${dataArray[1]}-${dataArray[0]}"
}

fun String.formatStringDateShow() : String{
    val dataArray = this.split("-")
    return "${dataArray[2]}/${dataArray[1]}/${dataArray[0]}"
}

fun String.formatStringTimeShow() : String{
    val timeArray = this.split(":")
    return "${timeArray[0]}:${timeArray[1]}"
}