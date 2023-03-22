package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.switchmaterial.SwitchMaterial

class ToDoAdapter(private val todoItems: MutableList<ToDo>): RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    class ToDoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val todoText: TextView = itemView.findViewById(R.id.todo_text)
        private val todoComplete: SwitchMaterial = itemView.findViewById(R.id.complete)

        fun bind(todo: ToDo)
        {
            todoText.text = todo.value
            todoComplete.isChecked = todo.completed
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_item, parent, false)
        return ToDoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val todo = todoItems[position]
        holder.bind(todo)
    }

    override fun getItemCount(): Int {
        return todoItems.size
    }
}