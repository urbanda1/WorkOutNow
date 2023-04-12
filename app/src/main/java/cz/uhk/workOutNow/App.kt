package cz.uhk.workOutNow

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import cz.uhk.workOutNow.di.databaseModule
import cz.uhk.workOutNow.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(listOf(uiModule, databaseModule))
        }

        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "General notifications"
            val description = "All notifications"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("general", name, importance).apply {
                this.description = description
            }

            val notificationmanager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationmanager.createNotificationChannel(channel)
        }
    }

}