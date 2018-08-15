package com.boxfox.android.myrelationshipsapplication.data.repository

import android.content.Context
import com.boxfox.android.myrelationshipsapplication.data.mapper.PeopleRealmMapper
import com.boxfox.android.myrelationshipsapplication.data.model.PeopleRealmObject
import com.boxfox.android.myrelationshipsapplication.domain.PeopleUsecase
import com.boxfox.android.myrelationshipsapplication.entity.People
import com.boxfox.android.myrelationshipsapplication.util.realm
import rx.Single

class PeopleRepository(private val ctx: Context) : PeopleUsecase {

    override fun getList(): Single<List<People>> {
        return Single.create { subscriber ->
            ctx.realm.where(PeopleRealmObject::class.java)
                    .findAllAsync()
                    .asObservable()
                    .subscribe {
                        subscriber.onSuccess(it.map { PeopleRealmMapper.fromRealmObject(it) })
                    }
        }
    }

    override fun search(text: String): Single<List<People>> {
        return Single.create { subscriber ->
            ctx.realm.where(PeopleRealmObject::class.java)
                    .contains("name", text)
                    .contains("phone", text)
                    .findAllAsync()
                    .asObservable()
                    .subscribe {
                subscriber.onSuccess(it.map { PeopleRealmMapper.fromRealmObject(it) })
            }
        }
    }

    override fun add(people: People): Single<Void> {
        return Single.create { subscriber ->
            ctx.realm.beginTransaction()
            ctx.realm.copyToRealm(PeopleRealmMapper.toRealmObject(people))
            ctx.realm.commitTransaction()
            subscriber.onSuccess(null)
        }
    }

    override fun remove(people: People): Single<Void> {
        return Single.create{ subscriber ->
            ctx.realm.beginTransaction()
            ctx.realm.where(PeopleRealmObject::class.java)
                    .equalTo("name", people.name)
                    .equalTo("phone", people.phone)
                    .equalTo("age", people.age)
                    .equalTo("address", people.address)
                    .findFirst().deleteFromRealm()
            ctx.realm.commitTransaction()
            subscriber.onSuccess(null)
        }
    }

}