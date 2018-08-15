package com.boxfox.android.myrelationshipsapplication.entity

class People {
    var id: Int = 0
    lateinit var name: String
    var age: Int = 0
    lateinit var phone: String
    lateinit var address: String
    lateinit var familys : List<Family>
    lateinit var storys : List<Story>
}