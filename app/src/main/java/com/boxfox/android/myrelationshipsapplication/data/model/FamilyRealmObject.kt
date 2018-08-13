package com.boxfox.android.myrelationshipsapplication.data.model

import io.realm.RealmObject

class FamilyRealmObject : RealmObject() {
    lateinit var name: String
    var age: Int = 0
    lateinit var relationShip: String
    lateinit var desc: String
}