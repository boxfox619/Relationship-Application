package com.boxfox.android.myrelationshipsapplication.data.repository

import android.content.Context
import android.util.Log
import com.boxfox.android.myrelationshipsapplication.data.mapper.PeopleRealmMapper
import com.boxfox.android.myrelationshipsapplication.data.model.PeopleRealmObject
import com.boxfox.android.myrelationshipsapplication.domain.PeopleUsecase
import com.boxfox.android.myrelationshipsapplication.entity.People
import io.realm.Realm
import io.realm.RealmObject
import io.realm.Sort
import rx.Single


class PeopleRepository(private val ctx: Context) : PeopleUsecase {

    override fun get(id: Int): Single<People> {
        return Single.create { subscriber ->
            val realm = Realm.getDefaultInstance()
            val people = realm.where(PeopleRealmObject::class.java).equalTo("id", id)
                    .findFirst()
            if(people!=null){
                subscriber.onSuccess(PeopleRealmMapper.fromRealmObject(people))
            }else{
                subscriber.onError(null)
            }
        }
    }

    override fun getList(): Single<List<People>> {
        return Single.create { subscriber ->
            val realm = Realm.getDefaultInstance()
            val peopleList = realm.where(PeopleRealmObject::class.java)
                    .findAllSortedAsync("id", Sort.DESCENDING)
            subscriber.onSuccess(peopleList.map { PeopleRealmMapper.fromRealmObject(it) })
        }
    }

    override fun search(text: String): Single<List<People>> {
        return Single.create { subscriber ->
            val realm = Realm.getDefaultInstance()
            val peopleList = realm.where(PeopleRealmObject::class.java)
                    .contains("name", text).or()
                    .contains("phone", text).or()
                    .contains("address",text)
                    .findAll()
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
            Log.d("Test", realm.where(PeopleRealmObject::class.java).count().toString())
            subscriber.onSuccess(null)
        }
    }





    override fun update(people: People): Single<Void> {
        return Single.create { subscriber ->
            remove(people.id).subscribe {
                add(people).subscribe {
                    subscriber.onSuccess(null)
                }
            }
        }
    }

    override fun remove(people: Int): Single<Void> {
        return Single.create { subscriber ->
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            val people = realm.where(PeopleRealmObject::class.java)
                    .equalTo("id", people)
                    .findFirst()
            RealmObject.deleteFromRealm(people)
            realm.commitTransaction()
            subscriber.onSuccess(null)
        }
    }

}