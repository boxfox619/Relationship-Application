package com.boxfox.android.myrelationshipsapplication.presentation.main

import com.boxfox.android.myrelationshipsapplication.data.repository.PeopleRepository
import com.boxfox.android.myrelationshipsapplication.presentation.Presenter

class MainPresenter(
        view: MainView,
        private val repository: PeopleRepository = PeopleRepository(view.getContext())
) : Presenter<MainView>(view){

    fun load(){
        view.visibleProgress(true)
        repository.getList().subscribe{
            view.setItems(it)
            view.visibleProgress(false)
        }
    }

    fun search(text: String){
        view.visibleProgress(true)
        repository.search(text).subscribe{
            view.setItems(it)
            view.visibleProgress(false)
        }
    }

    override fun destory() {

    }
}