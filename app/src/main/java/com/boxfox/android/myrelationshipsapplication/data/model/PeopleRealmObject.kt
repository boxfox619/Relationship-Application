package com.boxfox.android.myrelationshipsapplication.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey



class PeopleRealmObject : RealmObject() {
    @PrimaryKey
    var id: Int = 0
    lateinit var name: String
    var age: Int = 0
    lateinit var phone: String
    lateinit var address: String
    lateinit var familys : List<FamilyRealmObject>
    lateinit var storys : List<StoryRealmObject>
}