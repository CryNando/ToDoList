package com.example.luisfilho.todolist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.luisfilho.todolist.data.ToDoDAO
import com.example.luisfilho.todolist.data.ToDoListINSTANCE
import com.example.luisfilho.todolist.data.ToDoMODEL
import kotlinx.android.synthetic.main.add_todolist.*
import java.time.LocalDateTime


/**
 * Created by luis.filho on 20/08/2018.
 */
class AddToDoListActivity : AppCompatActivity() {

    private var todoDAO : ToDoDAO? = null
    private var todoItem : ToDoMODEL? = null
    private var viewModel : ToDoViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_todolist)

        var db : ToDoListINSTANCE = ToDoListINSTANCE.getInstance(this)!!
        var daoTodo = db.todolistDAO()
        viewModel = ViewModelProviders.of(this).get(ToDoViewModel::class.java)

        btnADD.setOnClickListener{
            var title : String = etTitle.text.toString()
            var description : String = etDescription.text.toString()
            var date = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocalDateTime.now().toString()
            } else {
                (System.currentTimeMillis()/1000).toString()
            }
            var todoItem = ToDoMODEL(title,description,date,false)
            viewModel!!.addItem(todoItem)


        }


    }





}