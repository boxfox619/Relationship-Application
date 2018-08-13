package com.boxfox.android.myrelationshipsapplication.data.mapper

import com.boxfox.android.myrelationshipsapplication.data.model.FamilyRealmObject
import com.boxfox.android.myrelationshipsapplication.entity.Family

object FamilyRealmMapper : RealmEntityMapper<Family, FamilyRealmObject> {
    override fun fromRealmObject(family: FamilyRealmObject): Family {
        return Family().apply{
            this.name = family.name
            this.relationShip = family.relationShip
            this.age = family.age
            this.desc = family.desc
        }
    }

    override fun toRealmObject(family: Family): FamilyRealmObject{
        return FamilyRealmObject().apply{
            this.name = family.name
            this.relationShip = family.relationShip
            this.age = family.age
            this.desc = family.desc
        }
    }
}