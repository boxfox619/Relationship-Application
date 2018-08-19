package com.boxfox.android.myrelationshipsapplication.data.model

import io.realm.RealmModel
import io.realm.annotations.RealmClass

@RealmClass
open class StoryRealmObject : RealmModel {
    lateinit var date: String
    lateinit var content: String
}