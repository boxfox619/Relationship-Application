package com.boxfox.android.myrelationshipsapplication.presentation.edit

import com.boxfox.android.myrelationshipsapplication.data.repository.PeopleRepository
import com.boxfox.android.myrelationshipsapplication.presentation.Presenter

class PeopleEditPresneter(
        view: PeopleEditView,
        private val repository: PeopleRepository = PeopleRepository(view.getContext())
) : Presenter<PeopleEditView>(view) {
    override fun destory() {
    }

}