package com.boxfox.android.myrelationshipsapplication.presentation.main

import com.boxfox.android.myrelationshipsapplication.data.repository.PeopleRepository
import com.boxfox.android.myrelationshipsapplication.presentation.Presenter

class MainPresenter(
        view: MainView,
        private val repository: PeopleRepository = PeopleRepository(view.getContext())
) : Presenter<MainView>(view) {

    fun load() {
        repository.getList().subscribe {
            view.setItems(it)
        }
    }

    fun search(text: String) {
        if (text.isNotEmpty()) {
            repository.search(text).subscribe {
                view.setItems(it)
            }
        } else {
            load()
        }
    }

    fun deletePeople(peopleId: Int){
        repository.remove(peopleId).subscribe{
            load()
        }
    }

    override fun destory() {

    }
}