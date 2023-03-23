package com.example.todolist

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString

class FileHelper {

    val FILENAME = "listinfo.dat"

    fun writeData(items: MutableList<ToDo>, context: Context)
    {
        val fos: FileOutputStream = context.openFileOutput(FILENAME, Context.MODE_PRIVATE)
        val oas = ObjectOutputStream(fos)
        val toDoList = object : TypeToken<MutableList<ToDo>>() {}.type
        val json = Gson().toJson(items, toDoList)
        oas.writeObject(json)
        oas.close()
    }

    fun readData(context: Context): MutableList<ToDo>
    {
        val fis: FileInputStream = context.openFileInput(FILENAME)
        val ois = ObjectInputStream(fis)
        val serializedStream = ois.readObject() as String
        ois.close()
        val toDoList = object : TypeToken<MutableList<ToDo>>() {}.type
        val list = Gson().fromJson<MutableList<ToDo>>(serializedStream, toDoList)
        Log.d("FileHelper", "Found file with list of size ${list.size}")
        return list
    }
}