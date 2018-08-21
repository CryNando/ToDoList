package com.example.luisfilho.todolist.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by luis.filho on 17/08/2018.
 */

@Database(entities = arrayOf(ToDoMODEL::class),version = 1, exportSchema = false)
abstract  class ToDoListINSTANCE : RoomDatabase(){
    companion object {
        private var INSTANCE : ToDoListINSTANCE? = null

        fun getInstance(context: Context) : ToDoListINSTANCE?{
            if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, ToDoListINSTANCE::class.java, "TodoList.db").build()
            }
            return INSTANCE
        }

        fun destroyInstace(){
            INSTANCE = null
        }

    }

    abstract fun todolistDAO() : ToDoDAO
}