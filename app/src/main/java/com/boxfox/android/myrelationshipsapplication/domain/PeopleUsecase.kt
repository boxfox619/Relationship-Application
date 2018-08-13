package com.boxfox.android.myrelationshipsapplication.domain

import com.boxfox.android.myrelationshipsapplication.entity.People
import rx.Observable
import rx.Single

interface PeopleUsecase : Usecase {
    fun getList(): Observable<List<People>>
    fun add(people: People): Single<Boolean>
    fun remove(people: People): Single<Boolean>
}