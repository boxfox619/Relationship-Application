package com.boxfox.android.myrelationshipsapplication.presentation.main

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import com.boxfox.android.myrelationshipsapplication.R
import com.boxfox.android.myrelationshipsapplication.entity.People
import com.boxfox.android.myrelationshipsapplication.presentation.BaseActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainPresenter>(), MainView {
    override val presenter: MainPresenter = MainPresenter(this)
    private lateinit var peopleAdpater: PeopleListAdapter
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Realm.init(this)
        this.peopleAdpater = PeopleListAdapter(applicationContext)
        recyclerview_main.adapter = peopleAdpater
        presenter.load()
        btn_main_search.setOnClickListener{presenter.search(et_main_search.text.toString())}
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