package com.boxfox.android.myrelationshipsapplication.presentation.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.boxfox.android.myrelationshipsapplication.R

class PeopleCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    constructor(context: Context, parent: ViewGroup) : this(LayoutInflater.from(context).inflate(R.layout.view_people_list_card, parent, false))

    fun setName(name: String){
        itemView.findViewById<TextView>(R.id.tv_people_list_name).text = name
    }
}
