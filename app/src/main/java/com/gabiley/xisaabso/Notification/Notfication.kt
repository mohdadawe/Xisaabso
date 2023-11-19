package com.gabiley.xisaabso.Notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.gabiley.xisaabso.MainActivity
import com.gabiley.xisaabso.R


class Notfication(
    var context: Context,
    var title: String,
    var msg: String
) {

    val channelID: String = "Welcome"
    val channelName: String = "My Notification"
    val notificationManager = context.applicationContext.getSystemService(
        Context.NOTIFICATION_SERVICE
    ) as NotificationManager

    lateinit var notificationChannel: NotificationChannel
    lateinit var notificationBuilder: NotificationCompat.Builder

    @RequiresApi(Build.VERSION_CODES.O)
    fun firNotification() {

        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent, PendingIntent.FLAG_IMMUTABLE
        )

        notificationChannel = NotificationChannel(
            channelID, channelName, NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(notificationChannel)
        notificationBuilder = NotificationCompat.Builder(context, channelID)
        notificationBuilder.setSmallIcon(R.drawable.ic_launcher_background)
        notificationBuilder.addAction(
            R.drawable.ic_launcher_background, "open Activity", pendingIntent
        )
        notificationBuilder.setContentTitle(title)
        notificationBuilder.setContentText(msg)
        notificationBuilder.setAutoCancel(true)

        notificationManager.notify(1, notificationBuilder.build())


    }




}