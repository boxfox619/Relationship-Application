package com.boxfox.android.myrelationshipsapplication.data.mapper

interface RealmEntityMapper<Entity, RealmObject> {
    fun fromRealmObject(realmObject: RealmObject): Entity
    fun toRealmObject(entity: Entity): RealmObject
}