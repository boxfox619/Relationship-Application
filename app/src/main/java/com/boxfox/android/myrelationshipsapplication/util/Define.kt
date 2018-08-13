package com.boxfox.android.myrelationshipsapplication.util

import android.content.Context
import io.realm.Realm

val Context.realm: Realm
    get() = Realm.getDefaultInstance()