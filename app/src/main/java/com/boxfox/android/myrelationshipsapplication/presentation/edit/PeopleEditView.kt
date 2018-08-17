package com.boxfox.android.myrelationshipsapplication.presentation.edit

import android.app.DatePickerDialog
import com.boxfox.android.myrelationshipsapplication.presentation.BaseView

interface PeopleEditView : BaseView{

    fun showDatePicker(listener: DatePickerDialog.OnDateSetListener): Unit

}