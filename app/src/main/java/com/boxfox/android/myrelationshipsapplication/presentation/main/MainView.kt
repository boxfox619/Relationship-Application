package com.boxfox.android.myrelationshipsapplication.presentation.main

import com.boxfox.android.myrelationshipsapplication.entity.People
import com.boxfox.android.myrelationshipsapplication.presentation.BaseView

interface MainView : BaseView {
    fun setItems(peopleList: List<People>): Unit
}