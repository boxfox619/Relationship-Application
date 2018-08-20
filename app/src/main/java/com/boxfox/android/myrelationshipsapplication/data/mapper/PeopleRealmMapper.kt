package com.boxfox.android.myrelationshipsapplication.data.mapper

import com.boxfox.android.myrelationshipsapplication.data.model.FamilyRealmObject
import com.boxfox.android.myrelationshipsapplication.data.model.PeopleRealmObject
import com.boxfox.android.myrelationshipsapplication.data.model.StoryRealmObject
import com.boxfox.android.myrelationshipsapplication.entity.People
import com.boxfox.android.myrelationshipsapplication.util.AgeCalHelper
import io.realm.RealmList

object PeopleRealmMapper : RealmEntityMapper<People, PeopleRealmObject> {
    override fun fromRealmObject(people: PeopleRealmObject): People {
        return People().apply {
            id = people.id
            name = people.name
            age = AgeCalHelper.toAge(people.age)
            phone = people.phone
            address = people.address
            familys = people.familys.map { FamilyRealmMapper.fromRealmObject(it) }
            storys = people.storys.map { StoryRealmMapper.fromRealmObject(it) }
        }
    }

    override fun toRealmObject(people: People): PeopleRealmObject {
        val familyRealmList = RealmList<FamilyRealmObject>()
        val storyRealmList = RealmList<StoryRealmObject>()
        people.familys.map { FamilyRealmMapper.toRealmObject(it) }.forEach{familyRealmList.add(it)}
        people.storys.map { StoryRealmMapper.toRealmObject(it) }.forEach{storyRealmList.add(it)}
        return PeopleRealmObject().apply {
            id = people.id
            name = people.name
            age = AgeCalHelper.toYear(people.age)
            phone = people.phone
            address = people.address
            familys = familyRealmList
            storys = storyRealmList
        }
    }
}