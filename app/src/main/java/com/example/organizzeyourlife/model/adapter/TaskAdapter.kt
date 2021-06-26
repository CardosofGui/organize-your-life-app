package com.example.organizzeyourlife.model.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.organizzeyourlife.R
import com.example.organizzeyourlife.databinding.CardviewTaskBinding
import com.example.organizzeyourlife.extensions.formatStringDateShow
import com.example.organizzeyourlife.extensions.formatStringTimeShow
import com.example.organizzeyourlife.model.Task
import com.example.organizzeyourlife.model.TaskInfo
import java.util.*
import kotlin.collections.ArrayList

class TaskAdapter(
    var listTask : ArrayList<TaskInfo>,
    val clickEdit : ((Int) -> Unit)
) : RecyclerView.Adapter<taskViewHolder>(){

    fun updateList(list : ArrayList<TaskInfo>){
        this.listTask = list
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): taskViewHolder {
        return taskViewHolder(CardviewTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = listTask.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: taskViewHolder, position: Int) {
        val task = listTask[position]
        val binding = holder.binding


        binding.txtTitleTask.text = task.task
        binding.txtDescTask.text = task.description

        if(task.date != null){
            binding.txtDataDesc.text = task.date.formatStringDateShow()
        }else{
            binding.txtDataDesc.text = "null"
        }

        binding.txtHoraTask.text = task.time?.formatStringTimeShow()

        binding.cardTask.setOnLongClickListener { view ->

            val popupMenu = PopupMenu(view.context, view)
            popupMenu.menuInflater.inflate(R.menu.menu_item, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.itemEditar -> {
                        clickEdit(position)
                    }
                }

                return@setOnMenuItemClickListener false
            }

            popupMenu.show()

            return@setOnLongClickListener false
        }
    }

}

class taskViewHolder(val binding : CardviewTaskBinding) : RecyclerView.ViewHolder(binding.root)