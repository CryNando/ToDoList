package com.example.luisfilho.todolist.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE

/**
 * Created by luis.filho on 17/08/2018.
 */
@Dao
interface ToDoDAO {

    @Query("SELECT * FROM TodoList")
    fun getAll() : LiveData<List<ToDoMODEL>>

    @Insert(onConflict = REPLACE)
    fun insert(todolist : ToDoMODEL)

    @Delete()
    fun delete(todolist: ToDoMODEL?)


    @Update
    fun update(todolist: ToDoMODEL?)


}