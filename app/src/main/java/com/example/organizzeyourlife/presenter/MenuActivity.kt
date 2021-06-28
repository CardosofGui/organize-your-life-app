package com.example.organizzeyourlife.presenter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.organizzeyourlife.R
import com.example.organizzeyourlife.databinding.ActivityMenuBinding
import com.example.organizzeyourlife.data.TaskListData
import com.example.organizzeyourlife.domain.TaskInfo
import com.example.organizzeyourlife.domain.adapter.TaskAdapter
import com.example.organizzeyourlife.framework.viewmodel.TaskViewModel
import es.dmoral.toasty.Toasty


class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private lateinit var taskViewModel : TaskViewModel

    private lateinit var adapterTask: TaskAdapter
    private lateinit var simpleCallBack: ItemTouchHelper.SimpleCallback

    private var listTask = ArrayList<TaskInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        initCallBack()
        initDados()
        initClicks()
    }

    private fun initCallBack() {
        simpleCallBack = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.RIGHT) {
                    taskViewModel.deleteTask(viewHolder.adapterPosition)
                    Toasty.success(this@MenuActivity, "Tarefa Finalizada", Toast.LENGTH_SHORT, true).show()
                } else if (direction == ItemTouchHelper.LEFT) {
                    AlertDialog.Builder(this@MenuActivity)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Excluir tarefa")
                        .setMessage("Você tem certeza que deseja excluir essa tarefa?")
                        .setPositiveButton("Excluir"
                        ) { _, _ ->
                            taskViewModel.deleteTask(viewHolder.adapterPosition)
                            Toasty.info(this@MenuActivity, "Tarefa excluida", Toast.LENGTH_LONG).show()
                            adapterTask.notifyDataSetChanged()

                            if(adapterTask.listTask.size == 0) binding.emptyState.llnEmptyState.visibility = View.VISIBLE
                        }
                        .setNegativeButton("Cancelar")
                        { _, _ ->
                            adapterTask.notifyDataSetChanged()
                        }
                        .show()
                }

                adapterTask.notifyDataSetChanged()
            }

            @SuppressLint("ResourceAsColor")
            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                val direction = if(dX > 0) "DIRECTION_RIGHT" else "DIRECTION_LEFT"
                val itemView = viewHolder.itemView

                when(direction){
                    "DIRECTION_RIGHT" -> {
                        val bg = ColorDrawable()
                        bg.color = Color.parseColor("#75D86E")
                        bg.setBounds(itemView.left, itemView.top, itemView.right, itemView.bottom)
                        bg.draw(c)

                        val icon = ActivityCompat.getDrawable(this@MenuActivity, R.drawable.ic_baseline_check_24)
                        val top = ((itemView.height) / 2) - (icon!!.intrinsicHeight / 2) + itemView.top
                        icon.setBounds(20, top + 5, 30 + icon.intrinsicWidth, top + icon.intrinsicHeight + 5)
                        icon.draw(c)
                    }
                    "DIRECTION_LEFT" -> {
                        val bg = ColorDrawable()
                        bg.color = Color.parseColor("#B63434")
                        bg.setBounds(itemView.left, itemView.top, itemView.right, itemView.bottom)
                        bg.draw(c)

                        val icon = ActivityCompat.getDrawable(this@MenuActivity, R.drawable.ic_baseline_delete)
                        val topLeft = ((itemView.height) / 2) - (icon!!.intrinsicHeight / 2) + itemView.top
                        icon.setBounds(itemView.right - icon.intrinsicWidth - 30, topLeft + 5, itemView.right - 20, topLeft + icon.intrinsicHeight + 5)
                        icon.draw(c)
                    }
                }


                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }
    }



    private fun initDados() {
        initRecycler(listTask)

        val itemTouchHelper = ItemTouchHelper(simpleCallBack)
        itemTouchHelper.attachToRecyclerView(binding.recyclerHoje)

        taskViewModel.listTask.observe(this, {
            if (it?.taskInfo.isNullOrEmpty() || it == null) {
                binding.emptyState.llnEmptyState.visibility = View.VISIBLE
            } else {
                binding.emptyState.llnEmptyState.visibility = View.GONE

                adapterTask.updateList(TaskListData.getList())
            }
        })
        taskViewModel.statusDeleteTask.observe(this, {
            if(it == true){
                adapterTask.notifyDataSetChanged()
            }
        })
    }

    private fun initRecycler(task: ArrayList<TaskInfo>) {
        adapterTask = TaskAdapter(task) { clickEdit(it) }
        binding.recyclerHoje.layoutManager = LinearLayoutManager(this)
        binding.recyclerHoje.adapter = adapterTask
    }

    private fun initClicks() {
        binding.btnCriarTask.setOnClickListener {
            startActivity(Intent(this, NewTask::class.java))
        }
    }

    private fun clickEdit(i: Int) {
        val intent = Intent(this, NewTask::class.java)
        intent.also {
            it.putExtra(TASK_ID, i)
            startActivity(it)
        }
    }

    override fun onResume() {
        super.onResume()
        taskViewModel.getAllTask()
    }

    companion object{
        const val TASK_ID = "task_id"
    }
}