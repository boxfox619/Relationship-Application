package com.boxfox.android.myrelationshipsapplication.data.repository

import android.content.Context
import com.boxfox.android.myrelationshipsapplication.data.mapper.PeopleRealmMapper
import com.boxfox.android.myrelationshipsapplication.data.model.PeopleRealmObject
import com.boxfox.android.myrelationshipsapplication.domain.PeopleUsecase
import com.boxfox.android.myrelationshipsapplication.entity.People
import io.realm.Realm
import io.realm.RealmObject
import rx.Single



class PeopleRepository(private val ctx: Context) : PeopleUsecase {


    override fun get(id: Int): Single<People> {
        return Single.create { subscriber ->
            val realm = Realm.getDefaultInstance()
            val people = realm.where(PeopleRealmObject::class.java)
                    .findFirstAsync()
            subscriber.onSuccess(PeopleRealmMapper.fromRealmObject(people))
        }
    }

    override fun getList(): Single<List<People>> {
        return Single.create { subscriber ->
            val realm = Realm.getDefaultInstance()
            val peopleList = realm.where(PeopleRealmObject::class.java)
                    .findAllAsync()
            subscriber.onSuccess(peopleList.map { PeopleRealmMapper.fromRealmObject(it) })
        }
    }

    override fun search(text: String): Single<List<People>> {
        return Single.create { subscriber ->
            val realm = Realm.getDefaultInstance()
            val peopleList = realm.where(PeopleRealmObject::class.java)
                    .contains("name", text)
                    .contains("phone", text)
                    .findAllAsync()
            subscriber.onSuccess(peopleList.map { PeopleRealmMapper.fromRealmObject(it) })
            }
    }

    override fun add(people: People): Single<Void> {
        return Single.create { subscriber ->
            val realm = Realm.getDefaultInstance()
            val currentIdNum = realm.where(PeopleRealmObject::class.java).max("id")
            val nextId: Int
            if (currentIdNum == null) {
                nextId = 1
            } else {
                nextId = currentIdNum!!.toInt() + 1
            }
            val peopleObject = PeopleRealmMapper.toRealmObject(people)
            peopleObject.id = nextId
            realm.beginTransaction()
            realm.copyToRealm(peopleObject)
            realm.commitTransaction()
            subscriber.onSuccess(null)
        }
    }

    override fun remove(people: People): Single<Void> {
        return Single.create{ subscriber ->
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            val people = realm.where(PeopleRealmObject::class.java)
                    .equalTo("id", people.id)
                    .findFirst()
            RealmObject.deleteFromRealm(people)
            realm.commitTransaction()
            subscriber.onSuccess(null)
        }
    }

}