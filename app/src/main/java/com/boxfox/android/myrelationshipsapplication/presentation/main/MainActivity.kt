package com.boxfox.android.myrelationshipsapplication.presentation.main

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.boxfox.android.myrelationshipsapplication.R
import com.boxfox.android.myrelationshipsapplication.entity.People
import com.boxfox.android.myrelationshipsapplication.presentation.BaseActivity
import com.boxfox.android.myrelationshipsapplication.presentation.edit.PeopleEditActivity

class MainActivity : BaseActivity<MainPresenter>(), MainView {
    override val presenter: MainPresenter = MainPresenter(this)
    private lateinit var peopleAdpater: PeopleListAdapter
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this, PeopleEditActivity::class.java))
    }

    override fun setItems(peopleList: List<People>) {
        peopleAdpater.clear()
        peopleAdpater.addItems(peopleList)
    }

    override fun getContext(): Context {
        return this
    }

    override fun visibleProgress(visible: Boolean) {
        if (visible) {
            this.progressDialog = ProgressDialog.show(this, "", "");
        } else if (this.progressDialog != null) {
            this.progressDialog.dismiss()
        }
    }
}
