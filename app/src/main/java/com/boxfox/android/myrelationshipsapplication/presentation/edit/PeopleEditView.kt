package com.boxfox.android.myrelationshipsapplication.presentation.edit

import android.app.DatePickerDialog
import com.boxfox.android.myrelationshipsapplication.entity.People
import com.boxfox.android.myrelationshipsapplication.presentation.BaseView

interface PeopleEditView : BaseView{

    fun initData(data: People): Unit
    fun getData(): People
    fun showDatePicker(listener: DatePickerDialog.OnDateSetListener): Unit
    fun showAddressPicker(): Unit
    fun finish(): Unit
}