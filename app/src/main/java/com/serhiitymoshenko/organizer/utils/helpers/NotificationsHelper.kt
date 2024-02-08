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
import com.serhiitymoshenko.organizer.ui.MainActivity
import com.serhiitymoshenko.organizer.utils.NOTIFICATION_CHANNEL_ID
import com.serhiitymoshenko.organizer.utils.NOTIFICATION_CHANNEL_NAME
import com.serhiitymoshenko.organizer.utils.NOTIFICATION_ID

class NotificationsHelper(private val context: Context) {

    private val contentIntent by lazy {
        PendingIntent.getActivity(
            context,
            0,
            Intent(context, MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )
    }

    private val notificationBuilder: NotificationCompat.Builder by lazy {
        NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setContentIntent(contentIntent)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(false) // Until Android 14
            .setAutoCancel(true)
    }

    private val notificationManager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_LOW
            )
            notificationBuilder.setChannelId(NOTIFICATION_CHANNEL_ID)
        }
    }

    fun getNotification(): Notification {
        createChannel()

        return notificationBuilder.build()
    }

    fun updateNotification(text: String?) {
        text?.let {
            notificationBuilder.setContentTitle(text)
        }

        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }
}