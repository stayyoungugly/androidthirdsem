package com.example.androidsemthree

import android.app.Application
import androidx.room.Room
import com.example.androidsemthree.database.AppDatabase
import com.example.androidsemthree.database.dao.NoteDao

class App : Application() {
    lateinit var database: AppDatabase
    lateinit var noteDao: NoteDao

    companion object {
        private lateinit var instance: App

        fun getInstance(): App {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app-db"
        ).run {
            allowMainThreadQueries()
            build()
        }
        noteDao = database.noteDao()
    }
}
