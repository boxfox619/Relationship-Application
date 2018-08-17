package com.boxfox.android.myrelationshipsapplication.presentation.edit

import com.boxfox.android.myrelationshipsapplication.data.repository.PeopleRepository
import com.boxfox.android.myrelationshipsapplication.presentation.Presenter
import android.app.DatePickerDialog.OnDateSetListener
import android.widget.EditText


class PeopleEditPresneter(
        view: PeopleEditView,
        private val repository: PeopleRepository = PeopleRepository(view.getContext())
) : Presenter<PeopleEditView>(view) {
    override fun destory() {
    }

    fun onDateFieldClicked(et: EditText){
        val listener = OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            et.setText(String.format("%04d-%02d-%02d", year, monthOfYear, dayOfMonth))
        }
        view.showDatePicker(listener)
    }

}