package com.boxfox.android.myrelationshipsapplication.presentation.edit

import com.boxfox.android.myrelationshipsapplication.data.repository.PeopleRepository
import com.boxfox.android.myrelationshipsapplication.presentation.Presenter
import android.app.DatePickerDialog.OnDateSetListener
import android.util.Log
import android.widget.TextView


class PeopleEditPresneter(
        view: PeopleEditView,
        private val repository: PeopleRepository = PeopleRepository(view.getContext())
) : Presenter<PeopleEditView>(view) {
    var peopleId: Int = -1
    override fun destory() {
    }

    fun onDateFieldClicked(et: TextView){
        val listener = OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            et.text = String.format("%04d-%02d-%02d", year, monthOfYear+1, dayOfMonth)
            this.view.sortEpisodeByDate()
        }
        view.showDatePicker(listener)
    }

    fun init(peopleId: Int) {
        this.peopleId = peopleId
        if (peopleId >= 0) {
            this.repository.get(peopleId).subscribe {
                if (it != null) {
                    view.initData(it)
                }
            }
        }
    }

    fun save(){
        val people = view.getData()
        if(peopleId >= 0){
            people.id = peopleId
            repository.update(people).subscribe({view.finish()},{
                Log.d("TEST", "asdasd")
            })
        }else{
            repository.add(people).subscribe({view.finish()},{
                Log.d("TEST", "asdasd")
            })
        }

    }

}