package com.boxfox.android.myrelationshipsapplication.data.mapper

import com.boxfox.android.myrelationshipsapplication.data.model.PeopleRealmObject
import com.boxfox.android.myrelationshipsapplication.entity.People

object PeopleRealmMapper : RealmEntityMapper<People, PeopleRealmObject> {
    override fun fromRealmObject(people: PeopleRealmObject): People {
        return People().apply {
            name = people.name
            age = people.age
            phone = people.phone
            address = people.address
            familys = people.familys.map { FamilyRealmMapper.fromRealmObject(it) }
            storys = people.storys.map { StoryRealmMapper.fromRealmObject(it) }
        }
    }

    override fun toRealmObject(people: People): PeopleRealmObject {
        return PeopleRealmObject().apply {
            name = people.name
            age = people.age
            phone = people.phone
            address = people.address
            familys = people.familys.map { FamilyRealmMapper.toRealmObject(it) }
            storys = people.storys.map { StoryRealmMapper.toRealmObject(it) }
        }
    }
}