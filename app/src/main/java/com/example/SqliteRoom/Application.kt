package com.example.SqliteRoom

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.util.Log

@Entity
class Application(
     @ColumnInfo(name = "package_name") var packageName: String,
    var ledColor: Int,
    @Ignore  var vibration: Int,
     @PrimaryKey @ColumnInfo(name = "app_name") var appName: String
    ) {

    constructor() : this("", 0, 0, "")

    override fun toString(): String {
        var str="Application(packageName=$packageName, ledColor=$ledColor, vibration=$vibration, appName=$appName)"
        Log.d("test",str)
        return str
    }
}