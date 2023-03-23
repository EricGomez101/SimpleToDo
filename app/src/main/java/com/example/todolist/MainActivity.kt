package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ActivityMainBinding
import java.io.FileNotFoundException

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
            todoList.add(ToDo(_binding.todoText.text.toString(), false))
            toDoAdapter.notifyItemInserted(todoList.size - 1)
            _binding.todoText.text.clear()
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