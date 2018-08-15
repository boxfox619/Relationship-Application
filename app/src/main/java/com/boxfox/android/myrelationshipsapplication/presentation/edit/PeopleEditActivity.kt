package com.boxfox.android.myrelationshipsapplication.presentation.edit

import android.os.Bundle
import com.boxfox.android.myrelationshipsapplication.R
import com.boxfox.android.myrelationshipsapplication.presentation.BaseActivity

class PeopleEditActivity : BaseActivity<PeopleEditPresneter>(), PeopleEditView {
    override val presenter: PeopleEditPresneter = PeopleEditPresneter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people_edit)
    }
}
