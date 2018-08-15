package com.boxfox.android.myrelationshipsapplication.presentation.main

import android.content.Context
import android.view.ViewGroup
import com.boxfox.android.myrelationshipsapplication.entity.People
import com.boxfox.android.myrelationshipsapplication.presentation.AbstractRecyclerAdapter

class PeopleListAdapter(context: Context) : AbstractRecyclerAdapter<People, PeopleCardViewHolder>(context) {
    override fun onItemViewType(position: Int): Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleCardViewHolder {
        return PeopleCardViewHolder(context, parent)
    }

    override fun onBindViewHolder(holder: PeopleCardViewHolder, position: Int) {
        var model = getItem(position)
        if (model != null) {
            holder.setName(model.name)
        }
    }
}
