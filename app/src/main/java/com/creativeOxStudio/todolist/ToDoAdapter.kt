package com.creativeOxStudio.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter(private val todoItems: MutableList<ToDo>): RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    var onItemClick : ((ToDo, position: Int) -> Unit)? = null

    inner class ToDoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val todoText: TextView = itemView.findViewById(R.id.todo_text)
        private val item: LinearLayout = itemView.findViewById(R.id.item)

        fun bind(todo: ToDo)
        {
            todoText.text = todo.value
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

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(todo, position)
        }
    }

    override fun getItemCount(): Int {
        return todoItems.size
    }
}