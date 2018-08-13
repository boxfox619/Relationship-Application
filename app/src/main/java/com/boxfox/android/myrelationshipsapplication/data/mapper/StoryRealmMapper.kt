package com.boxfox.android.myrelationshipsapplication.data.mapper

import com.boxfox.android.myrelationshipsapplication.data.model.StoryRealmObject
import com.boxfox.android.myrelationshipsapplication.entity.Story

object StoryRealmMapper : RealmEntityMapper<Story, StoryRealmObject>{
    override fun fromRealmObject(story: StoryRealmObject): Story {
        return Story().apply{
            date = story.date
            content = story.content
        }
    }

    override fun toRealmObject(story: Story): StoryRealmObject{
        return StoryRealmObject().apply{
            date = story.date
            content = story.content
        }
    }
}