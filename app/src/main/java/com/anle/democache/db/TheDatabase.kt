package com.anle.democache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anle.democache.models.UserInfo

@Database(entities = [UserInfo::class], version = 2)
abstract class TheDatabase : RoomDatabase() {
    abstract fun getDao(): UserDao
}
