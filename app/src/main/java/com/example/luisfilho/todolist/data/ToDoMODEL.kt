package com.example.luisfilho.todolist.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull


/**
 * Created by luis.filho on 17/08/2018.
 */
@Entity(tableName = "TodoList")
data class ToDoMODEL(
        @ColumnInfo(name="title")
        @NonNull
        var title : String = "",

        @ColumnInfo(name="description")
        var description : String = " ",

        @PrimaryKey
        @ColumnInfo(name="createdDate")
        var createdDate : String = "",

        @ColumnInfo(name="done")
        @NonNull
        var done : Boolean = false
)