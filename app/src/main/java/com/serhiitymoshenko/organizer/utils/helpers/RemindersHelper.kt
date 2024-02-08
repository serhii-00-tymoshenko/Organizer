package com.serhiitymoshenko.organizer.utils.helpers

import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.timepicker.MaterialTimePicker
import com.serhiitymoshenko.organizer.R
import com.serhiitymoshenko.organizer.data.models.TaskReminderStatus

class RemindersHelper(
    private val activity: FragmentActivity,
    private val timePickedCallback: (Int, Int, TaskReminderStatus) -> Unit
) {

    fun showReminderAlertDialog() {
        val resources = activity.resources
        val title = resources.getString(R.string.reminder_dialog_title)

        MaterialAlertDialogBuilder(activity)
            .setTitle(title)
            .setPositiveButton(TaskReminderStatus.EVERY_DAY.name) { dialog, _ ->
                startTimePicker(TaskReminderStatus.EVERY_DAY)
                dialog.cancel()
            }
            .setNegativeButton(TaskReminderStatus.ONE_TIME.name) { dialog, _ ->
                startTimePicker(TaskReminderStatus.ONE_TIME)
                dialog.cancel()
            }
            .setNeutralButton(TaskReminderStatus.NONE.name) { dialog, _ ->
                startTimePicker(TaskReminderStatus.NONE)
                dialog.cancel()
            }
            .show()
    }

    private fun startTimePicker(status: TaskReminderStatus) {
        val positiveButtonText = activity.resources.getString(R.string.dialog_continue_text)

        val timePicker = MaterialTimePicker.Builder()
            .setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD)
            .setPositiveButtonText(positiveButtonText)
            .setTitleText("Choose time")
            .build()

        timePicker.addOnPositiveButtonClickListener {
            timePickedCallback.invoke(timePicker.hour, timePicker.minute, status)
        }
        timePicker.show(activity.supportFragmentManager, null)
    }
}