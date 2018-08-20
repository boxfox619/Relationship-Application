package com.boxfox.android.myrelationshipsapplication.data.mapper

import com.boxfox.android.myrelationshipsapplication.data.model.FamilyRealmObject
import com.boxfox.android.myrelationshipsapplication.entity.Family
import com.boxfox.android.myrelationshipsapplication.util.AgeCalHelper

object FamilyRealmMapper : RealmEntityMapper<Family, FamilyRealmObject> {
    override fun fromRealmObject(family: FamilyRealmObject): Family {
        return Family().apply{
            this.name = family.name
            this.relationship = family.relationship
            this.age = AgeCalHelper.toAge(family.age)
            this.desc = family.desc
        }
    }

    override fun toRealmObject(family: Family): FamilyRealmObject{
        return FamilyRealmObject().apply{
            this.name = family.name
            this.relationship = family.relationship
            this.age = AgeCalHelper.toYear(family.age)
            this.desc = family.desc
        }
    }
}