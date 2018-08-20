package com.boxfox.android.myrelationshipsapplication.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.boxfox.android.myrelationshipsapplication.R
import com.boxfox.android.myrelationshipsapplication.entity.People
import com.boxfox.android.myrelationshipsapplication.presentation.BaseActivity
import com.boxfox.android.myrelationshipsapplication.presentation.edit.PeopleEditActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainPresenter>(), MainView {
    override val presenter: MainPresenter = MainPresenter(this)
    private lateinit var peopleAdpater: PeopleListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Realm.init(this)
        this.peopleAdpater = PeopleListAdapter(applicationContext) {
            et_main_search.setText("")
            showEditActivity(it)
        }
        recyclerview_main.layoutManager = LinearLayoutManager(this)
        recyclerview_main.adapter = peopleAdpater
        presenter.load()
        btn_main_search.setOnClickListener { search() }
        btn_main_add.setOnClickListener { showEditActivity(-1) }
    }

    fun showEditActivity(peopleId: Int) {
        val intent = Intent(this, PeopleEditActivity::class.java)
        intent.putExtra("peopleId", peopleId)
        startActivity(intent)
    }

    override fun setItems(peopleList: List<People>) {
        peopleAdpater.clear()
        peopleAdpater.addItems(peopleList)
        peopleAdpater.notifyDataSetChanged()
    }

    override fun getContext(): Context {
        return this
    }

    override fun onResume() {
        super.onResume()
        search()
    }

    fun search() {
        this.presenter.search(et_main_search.text.toString())
    }

}