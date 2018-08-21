package com.example.luisfilho.todolist.data

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.example.luisfilho.todolist.MainActivity
import com.example.luisfilho.todolist.R
import com.example.luisfilho.todolist.ToDoViewModel
import kotlin.collections.ArrayList

/**
 * Created by luis.filho on 14/08/2018.
 */
class ToDoAdapter(items: ArrayList<ToDoMODEL>, longClick : View.OnLongClickListener, viewModel : ToDoViewModel) : RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {

    private var listItems: List<ToDoMODEL> = items
    private var longClickItem : View.OnLongClickListener = longClick
    private var viewModel : ToDoViewModel = viewModel


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtTitle.text = listItems[position].title
        holder.txtDescription.text = listItems[position].description
        holder.txtDate.text = listItems[position].createdDate
        holder.itemView.tag = listItems[position]
        holder.itemView.setOnLongClickListener(longClickItem)
        if(listItems[position].done){
            holder.cbDone.isChecked = true
            holder.cardView.setBackgroundColor(-16711936)
        }else{
            holder.cbDone.isChecked = false
            holder.cardView.setBackgroundColor(-65536)
        }
        holder.cbDone.setOnClickListener{
            updateItem(listItems[position])
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)!!
        val txtDescription: TextView = itemView.findViewById(R.id.txtDescription)!!
        val txtDate: TextView = itemView.findViewById(R.id.txDate)!!
        val cbDone: CheckBox = itemView.findViewById(R.id.cbDone)!!
        val cardView : CardView = itemView.findViewById(R.id.card_view)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView =  LayoutInflater.from(parent.context).inflate(R.layout.item_row,parent,false)
        return ViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    fun addItems(listItems: List<ToDoMODEL>){
        this.listItems = listItems
        notifyDataSetChanged()
    }

    fun updateItem(listItem :  ToDoMODEL){
        var newItem = ToDoMODEL(listItem.title,listItem.description,listItem.createdDate,!listItem.done)
        viewModel.updateItem(newItem)
        notifyDataSetChanged()
    }


}