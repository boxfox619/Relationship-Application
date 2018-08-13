package com.boxfox.android.myrelationshipsapplication.data.model

import io.realm.RealmObject

class StoryRealmObject : RealmObject(){
    lateinit var date: String
    lateinit var content: String
}