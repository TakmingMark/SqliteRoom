package com.example.SqliteRoom

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(
    entities = [ Application::class], version = 1)


abstract class AppDatabase : RoomDatabase() {
    companion object {
        private const val DB_NAME="AppDatabase"
        @Volatile private var INSTANCE:AppDatabase?=null

        fun getInstance(context:Context):AppDatabase{
            return INSTANCE?: synchronized(this){
                INSTANCE?: buildDatabase(context).also { INSTANCE=it
                }
            }
        }

        private fun buildDatabase(context:Context)= Room
            .databaseBuilder(context,AppDatabase::class.java,AppDatabase.DB_NAME)
            .allowMainThreadQueries()
            .build()

    }

    abstract fun applicationDao(): ApplicationDao


}