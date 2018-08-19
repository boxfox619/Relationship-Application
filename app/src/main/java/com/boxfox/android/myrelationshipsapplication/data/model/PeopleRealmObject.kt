package com.boxfox.android.myrelationshipsapplication.data.model

import io.realm.RealmList
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class PeopleRealmObject : RealmModel {
    @PrimaryKey
    var id: Int = 0
    lateinit var name: String
    var age: Int = 0
    lateinit var phone: String
    lateinit var address: String
    lateinit var familys : RealmList<FamilyRealmObject>
    lateinit var storys : RealmList<StoryRealmObject>
}