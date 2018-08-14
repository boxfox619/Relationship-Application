package com.boxfox.android.myrelationshipsapplication.presentation

abstract class Presenter<BaseView>(protected val view: BaseView){
     abstract fun destory(): Unit
}