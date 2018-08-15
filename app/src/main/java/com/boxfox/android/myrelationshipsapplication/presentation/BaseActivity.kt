package com.boxfox.android.myrelationshipsapplication.presentation

import android.content.Context
import android.support.v7.app.AppCompatActivity


abstract class BaseActivity<out T : Presenter<*>> : AppCompatActivity() , BaseView {

    abstract val presenter : T

    override fun onDestroy() {
        super.onDestroy()
        presenter.destory()
    }

    override fun getContext(): Context {
        return this
    }
}