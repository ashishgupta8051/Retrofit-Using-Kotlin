package com.example.learnretrofitusingkotlin.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learnretrofitusingkotlin.R
import com.example.learnretrofitusingkotlin.model.Post

class DataAdapter(private val context:Context) : RecyclerView.Adapter<DataAdapter.Holder>() {

    private var dataList:List<Post> = emptyList()

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idTxt:TextView = itemView.findViewById(R.id.id)
        val userIdTxt:TextView = itemView.findViewById(R.id.user_id)
        val titleTxt:TextView = itemView.findViewById(R.id.title)
        val bodyTxt:TextView = itemView.findViewById(R.id.body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.data_list,parent,false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.idTxt.text = "ID : "+dataList[position].id.toString()
        holder.userIdTxt.text = "USER ID : "+dataList[position].userId.toString()
        holder.titleTxt.text = "TITLE : "+dataList[position].title
        holder.bodyTxt.text = "BODY : "+dataList[position].body
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getData(list:List<Post>){
        dataList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
       return dataList.size
    }
}