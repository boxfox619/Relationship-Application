package com.boxfox.android.myrelationshipsapplication.data.model

import io.realm.RealmModel
import io.realm.annotations.RealmClass

@RealmClass
open class FamilyRealmObject : RealmModel {
    lateinit var name: String
    var age: Int = 0
    lateinit var relationShip: String
    lateinit var desc: String
}