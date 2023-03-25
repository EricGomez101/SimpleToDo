package com.creativeOxStudio.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.creativeOxStudio.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private lateinit var todoList: MutableList<ToDo>
    val fileHelper = FileHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)
        todoList = try {
            fileHelper.readData(this)
        } catch (_: Exception) {
            mutableListOf()
        }
        val toDoAdapter = ToDoAdapter(todoList)
        _binding.list.adapter = toDoAdapter

        _binding.submit.setOnClickListener {
            todoList.add(ToDo(_binding.todoText.text.toString()))
            toDoAdapter.notifyItemInserted(todoList.size - 1)
            _binding.todoText.text.clear()
        }

        toDoAdapter.onItemClick = { _, position ->
            todoList.removeAt(position)
            toDoAdapter.notifyItemRemoved(position)
        }
    }

    override fun onPause() {
        super.onPause()
        if (todoList.size > 0) {
            Log.d("onPause", "Writing to do list to file...")
            fileHelper.writeData(todoList, this)
        }

    }
}