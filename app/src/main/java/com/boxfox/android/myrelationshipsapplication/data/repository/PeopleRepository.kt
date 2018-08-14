package com.boxfox.android.myrelationshipsapplication.data.repository

import android.content.Context
import com.boxfox.android.myrelationshipsapplication.data.mapper.PeopleRealmMapper
import com.boxfox.android.myrelationshipsapplication.data.model.PeopleRealmObject
import com.boxfox.android.myrelationshipsapplication.domain.PeopleUsecase
import com.boxfox.android.myrelationshipsapplication.entity.People
import com.boxfox.android.myrelationshipsapplication.util.realm
import rx.Observable
import rx.Single

class PeopleRepository(private val ctx: Context) : PeopleUsecase {

    override fun search(text: String): Observable<List<People>> {
        return Observable.create { subscriber ->
            ctx.realm.where(PeopleRealmObject::class.java)
                    .contains("name", text)
                    .contains("phone", text)
                    .findAllAsync()
                    .asObservable()
                    .subscribe {
                subscriber.onNext(it.map { PeopleRealmMapper.fromRealmObject(it) })
            }
        }
    }

    override fun add(people: People): Single<Boolean> {
        return Single.create { subscriber ->
            ctx.realm.beginTransaction()
            ctx.realm.copyToRealm(PeopleRealmMapper.toRealmObject(people))
            ctx.realm.commitTransaction()
            subscriber.onSuccess(true)
        }
    }

    override fun remove(people: People): Single<Boolean> {
    }

}