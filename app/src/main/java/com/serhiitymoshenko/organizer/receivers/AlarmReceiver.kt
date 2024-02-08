package com.serhiitymoshenko.organizer.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.serhiitymoshenko.organizer.utils.TEXT_ARGUMENT_NAME
import com.serhiitymoshenko.organizer.utils.helpers.NotificationsHelper

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val text = intent?.getStringExtra(TEXT_ARGUMENT_NAME)

        context?.let {
            val notificationsHelper = NotificationsHelper(context)

            notificationsHelper.getNotification()
            notificationsHelper.updateNotification(text)
        }
    }
}