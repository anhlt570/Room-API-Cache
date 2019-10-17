package com.anle.democache

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.anle.democache.db.TheDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        app = this
    }

    companion object {
        lateinit var app: App
        val applicationContext: Context
            get() = app.baseContext

        val db = lazy {
            Room.databaseBuilder(applicationContext, TheDatabase::class.java, "My Database")
                .fallbackToDestructiveMigration()
                .build()
        }

    }
}