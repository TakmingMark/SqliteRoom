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

        //Volatile http://percent010.blogspot.com/2015/05/volatile-java.html
        @Volatile private var INSTANCE:AppDatabase?=null

        //Kotlin 如果要鎖住區塊的話，要使用synchronized(this) ps.如果要鎖住fun 要用 @synchronized
        fun getInstance(context:Context):AppDatabase{

//            INSTANCE= buildDatabase(context)
//            return INSTANCE!!

            //這段的意思是 A?:B 等於 if(A==null)執行B
            return INSTANCE?: synchronized(this){
                //also =>呼叫某對象的also，在函數裡會由某對象擔任it
                INSTANCE?: buildDatabase(context).also { INSTANCE=it
                }
            }
        }

        private fun buildDatabase(context:Context)= Room
            .databaseBuilder(context,AppDatabase::class.java,AppDatabase.DB_NAME)
            .allowMainThreadQueries() //允許在主線程執行，不然只能寫一個Thread執行
            .build()
    }

    abstract fun applicationDao(): ApplicationDao
}