package com.example.organizzeyourlife.presenter

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.organizzeyourlife.databinding.ActivityNewTaskBinding
import com.example.organizzeyourlife.data.TaskListData
import com.example.organizzeyourlife.extensions.formatStringDateShow
import com.example.organizzeyourlife.extensions.formatStringTimeShow
import com.example.organizzeyourlife.domain.TaskInfo
import com.example.organizzeyourlife.domain.singleton.UserSingleton
import com.example.organizzeyourlife.presenter.MenuActivity.Companion.TASK_ID
import com.example.organizzeyourlife.framework.viewmodel.TaskViewModel
import java.util.*

class NewTask : AppCompatActivity() {


    private var task : TaskInfo? = null
    private lateinit var binding : ActivityNewTaskBinding
    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var timePickDialog: TimePickerDialog
    private lateinit var taskViewModel : TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        if(intent.hasExtra(TASK_ID)){
            fillData(intent.getIntExtra(TASK_ID, 0))
        }

        initDados()
        createListeners()
        initClicks()
    }

    private fun fillData(index : Int) {
        task = TaskListData.findById(index)

        binding.edtTitulo.setText(task!!.task)
        binding.edtDescricao.setText(task!!.description)
        binding.edtTime.setText(task!!.time?.formatStringTimeShow())
        binding.edtData.setText(task!!.date?.formatStringDateShow())

        binding.toolbar.title = "Editar tarefa"
        binding.btnNewTask.text = "Editar"
        binding.txtCriandoTask.text = "Editando"
    }

    private fun initDados() {
        taskViewModel.statusNewTask.observe(this, {
            if(it){
                Toast.makeText(this, "Tarefa criada com sucesso", Toast.LENGTH_LONG).show()
                finish()
            }else if(!it){
                Toast.makeText(this, "Ocorreu um erro ao criar sua tarefa", Toast.LENGTH_LONG).show()
                blockInterface(true)
            }
        })
    }

    private fun createListeners() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        datePickerDialog = DatePickerDialog(this, { view, year, month, dayOfMonth ->
            val ano = if(year in 0..9) "0${year}" else "$year"
            val mes = if(month in 0..9) "0${month}" else "$month"
            val dia = if(dayOfMonth in 0..9) "0${dayOfMonth}" else "$dayOfMonth"

            binding.edtData.setText("$dia/$mes/$ano") },
            year,
            month,
            day)

        timePickDialog = TimePickerDialog(this, { view, hourOfDay, minute ->
            val hour = if(hourOfDay in 0..9) "0${hourOfDay}" else "$hourOfDay"
            val minute = if(minute in 0..9) "0${minute}" else "$minute"

            binding.edtTime.setText("$hour:$minute") },
            hour,
            minute,
            true)
    }

    private fun initClicks() {
        binding.edtData.setOnClickListener {
            datePickerDialog.show()
        }
        binding.edtTime.setOnClickListener {
            timePickDialog.show()
        }
        binding.btnCancel.setOnClickListener {
            finish()
        }
        binding.btnNewTask.setOnClickListener {
            if(binding.edtTitulo.text.isNullOrEmpty() || binding.edtDescricao.text.isNullOrEmpty() || binding.edtTime.text.isNullOrEmpty() ||binding.edtData.text.isNullOrEmpty()){
                Toast.makeText(this, "Erro - Preencha todos os campos", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val idTask = task?.idTask

            val taskCreated = TaskInfo(
                idTask,
                binding.edtTitulo.text.toString(),
                binding.edtDescricao.text.toString(),
                binding.edtData.text.toString(),
                binding.edtTime.text.toString(),
                UserSingleton.user!!.idUser
            )

            if(idTask != null) taskViewModel.updateTask(taskCreated)
            else taskViewModel.insertTask(taskCreated)
            blockInterface(false)
        }
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun blockInterface(state : Boolean){
        binding.edtTitulo.isEnabled = state
        binding.edtDescricao.isEnabled = state
        binding.edtData.isEnabled = state
        binding.edtTime.isEnabled = state
        binding.btnCancel.isEnabled = state
        binding.btnNewTask.isEnabled = state

        binding.progCriando.visibility = if(state) View.GONE else View.VISIBLE
        binding.txtCriandoTask.visibility = if(state) View.GONE else View.VISIBLE
    }
}

