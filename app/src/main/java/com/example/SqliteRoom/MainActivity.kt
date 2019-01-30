package com.example.SqliteRoom

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    var packageNames= arrayOf("Phone","SMS","Line","WhatsApp")


    lateinit var appDatabase:AppDatabase
    lateinit var applications:List<Application>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appDatabase= AppDatabase.getInstance(this)

        applications =getAll()

        for (packageName in packageNames)
            if(!isExistApplication(packageName))
                insert(packageName)

        update(packageNames[0])
        delete(packageNames[3])

        applications =getAll()

        for(application in applications)
            application.toString()

        /** print
         * Application(packageName=com.update.Phone, ledColor=0, vibration=0, appName=Phone)
         * Application(packageName=com.SMS, ledColor=0, vibration=0, appName=SMS)
         * Application(packageName=com.Line, ledColor=0, vibration=0, appName=Line)
         */
    }

    fun getAll()=appDatabase.applicationDao().getAll()

    fun insert(appName:String){
        var application=Application("com.$appName", 0, 0, appName)
        appDatabase.applicationDao().insert(application)
    }

    fun delete(appName:String){
        var application=Application("com.$appName", 0, 0, appName)
        appDatabase.applicationDao().delete(application)
    }

    fun update(appName: String){
        var application=Application("com.update.$appName", 0, 0, appName)
        appDatabase.applicationDao().update(application)
    }

    fun isExistApplication(appName:String):Boolean{
        var application=appDatabase.applicationDao().findByAppName(appName)
        return application!=null
    }

}
