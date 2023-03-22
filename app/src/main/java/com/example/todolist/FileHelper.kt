package com.example.todolist

import android.content.Context
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class FileHelper {

    val FILENAME = "listinfo.dat"

    fun writeData(item: MutableList<ToDo>, context: Context)
    {
        val fos: FileOutputStream = context.openFileOutput(FILENAME, Context.MODE_PRIVATE)
        val oas = ObjectOutputStream(fos)
        oas.writeObject(item)
        oas.close()
    }

    fun readData(context: Context): MutableList<ToDo>
    {
        val itemList: MutableList<ToDo>
        val fis: FileInputStream = context.openFileInput(FILENAME)
        val ois = ObjectInputStream(fis)
        itemList = ois.readObject() as MutableList<ToDo>
        return itemList
    }
}