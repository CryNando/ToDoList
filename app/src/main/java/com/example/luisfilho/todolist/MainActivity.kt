package com.example.luisfilho.todolist


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import com.example.luisfilho.todolist.data.ToDoAdapter
import com.example.luisfilho.todolist.data.ToDoMODEL
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnLongClickListener {
    private var viewModel : ToDoViewModel? = null
    private var myAdapterRecycle : ToDoAdapter? = null
    private var myRecycle : RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myRecycle = findViewById(R.id.recycleView)
        viewModel = ViewModelProviders.of(this).get(ToDoViewModel::class.java)
        myAdapterRecycle = ToDoAdapter(arrayListOf(),this, viewModel!!)
        myRecycle!!.layoutManager = LinearLayoutManager(this)
        myRecycle!!.adapter = myAdapterRecycle
        viewModel!!.getList().observe(this, Observer { items -> myAdapterRecycle!!.addItems(items!!) })


        btnNewTODO.setOnClickListener{
            var intent = Intent(this, AddToDoListActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onLongClick(v: View) : Boolean {
        var itemList : ToDoMODEL = v.tag as ToDoMODEL
        viewModel!!.deleteItem(itemList)
        return true
    }



}
