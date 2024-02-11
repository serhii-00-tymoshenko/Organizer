package com.serhiitymoshenko.organizer.utils.helpers

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.serhiitymoshenko.organizer.R
import com.serhiitymoshenko.organizer.data.models.Task
import com.serhiitymoshenko.organizer.ui.MainActivity
import com.serhiitymoshenko.organizer.utils.NOTIFICATION_CHANNEL_ID
import com.serhiitymoshenko.organizer.utils.NOTIFICATION_CHANNEL_NAME

class NotificationsHelper(private val context: Context, private val notificationId: Int) {


    private val contentIntent by lazy {
        PendingIntent.getActivity(
            context,
            notificationId,
            Intent(context, MainActivity::class.java),
            PendingIntent.FLAG_MUTABLE
        )
    }

    private val notificationBuilder: NotificationCompat.Builder by lazy {
        NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setContentIntent(contentIntent)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(false) // Until Android 14
            .setAutoCancel(false)
    }

    private val notificationManager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_LOW
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun getNotification(): Notification {
        createChannel()

        return notificationBuilder.build()
    }

    fun updateNotification(task: Task) {
        notificationBuilder.setContentTitle(task.title)
        notificationBuilder.setContentText(task.status.toString())

        notificationManager.notify(notificationId, notificationBuilder.build())
    }
}