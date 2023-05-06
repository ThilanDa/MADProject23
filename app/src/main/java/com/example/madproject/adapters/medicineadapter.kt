package com.example.madproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.madproject.model.MedicinesModel
import java.text.FieldPosition

class medicineadapter (private val medicineList:ArrayList<MedicinesModel>):RecycleView.adapter<medicineadapter.ViewHolder>(){

    private lateinit var mListener:onItemClickListner

    interface  onItemClickListner{
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(clickListener: onItemClickListner){
        mListener=clickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int):ViewHolder{



        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.medi_list_item,parent,false)
        return ViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder:medicineadapter.ViewHolder,position:Int){
        val currentMedi=empList[position]
        holder.tvMediName.text=currentMedi.mediName
    }

    override fun getItemCount():Int{
        return medilist.size
    }

    class ViewHolder (itemView:View,clickListener: onItemClickListner):RecyclerView.ViewHolder(itemView){
        val tvMediName:TextView = itemView.findViewById(R.id.tvMediName)

        init{
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}