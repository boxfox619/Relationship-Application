package com.boxfox.android.myrelationshipsapplication.domain

import com.boxfox.android.myrelationshipsapplication.entity.People
import rx.Observable
import rx.Single

interface PeopleUsecase : Usecase {
    fun search(text: String): Observable<List<People>>
    fun add(people: People): Single<Boolean>
    fun remove(people: People): Single<Boolean>
}