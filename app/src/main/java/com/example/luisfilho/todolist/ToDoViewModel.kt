package com.example.luisfilho.todolist

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.example.luisfilho.todolist.data.ToDoListINSTANCE
import com.example.luisfilho.todolist.data.ToDoMODEL

/**
 * Created by luis.filho on 20/08/2018.
 */
class ToDoViewModel(application: Application) : AndroidViewModel(application) {

    private var listItens : LiveData<List<ToDoMODEL>>
    private var db : ToDoListINSTANCE

    init{
        db = ToDoListINSTANCE.getInstance(this.getApplication())!!
        listItens = db.todolistDAO().getAll()
    }

    fun getList() : LiveData<List<ToDoMODEL>>{
        return listItens
    }

    fun addItem(item : ToDoMODEL){
        addAsyncTask(db).execute(item)
    }

    fun deleteItem(item: ToDoMODEL){
        deleteAsyncTask(db).execute(item)
    }

    fun updateItem(item: ToDoMODEL){
        updateAsyncTask(db).execute(item)
    }


    class updateAsyncTask(db : ToDoListINSTANCE) : AsyncTask<ToDoMODEL,Void,Void>(){
        private var todoDB = db
        override fun doInBackground(vararg params: ToDoMODEL?): Void? {
            todoDB.todolistDAO().update(params[0])
            return null
        }

    }

    class deleteAsyncTask(db : ToDoListINSTANCE) : AsyncTask<ToDoMODEL,Void,Void>(){
        private var todoDB = db
        override fun doInBackground(vararg params: ToDoMODEL?): Void? {
           todoDB.todolistDAO().delete(params[0])
            return null
        }

    }


    class addAsyncTask(db : ToDoListINSTANCE) : AsyncTask<ToDoMODEL, Void, Void>(){
        private var todoDB = db
        override fun doInBackground(vararg params: ToDoMODEL): Void? {
            todoDB.todolistDAO().insert(params[0])
            return null
        }

    }





}