package com.boxfox.android.myrelationshipsapplication.domain

import com.boxfox.android.myrelationshipsapplication.entity.People
import rx.Single

interface PeopleUsecase : Usecase {
    fun getList(): Single<List<People>>
    fun search(text: String): Single<List<People>>
    fun add(people: People): Single<Void>
    fun remove(people: People): Single<Void>
}