package com.example.SqliteRoom

import android.arch.persistence.room.*

@Dao
interface ApplicationDao {
    @Query("SELECT * FROM application")
    fun getAll(): List<Application>

    @Query("SELECT * FROM application WHERE app_name LIKE :appName LIMIT 1")
    fun findByAppName(appName: String): Application?

    @Update
    fun update(vararg apps: Application)

    @Insert
    fun insert(vararg apps: Application)

    @Delete
    fun delete(app: Application)

}