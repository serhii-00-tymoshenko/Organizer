package com.serhiitymoshenko.organizer.ui.home.todo.children.addtask

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.serhiitymoshenko.organizer.data.db.entities.TaskEntity
import com.serhiitymoshenko.organizer.data.models.task.TaskReminderStatus
import com.serhiitymoshenko.organizer.data.models.task.TaskStatus
import com.serhiitymoshenko.organizer.databinding.FragmentAddTaskBinding
import com.serhiitymoshenko.organizer.ui.home.todo.children.addtask.viewmodel.AddTaskViewModel
import com.serhiitymoshenko.organizer.utils.helpers.PermissionsHelper
import com.serhiitymoshenko.organizer.utils.helpers.RemindersHelper
import org.koin.android.ext.android.inject

class AddTaskFragment : Fragment() {

    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!

    private val viewModel by inject<AddTaskViewModel>()

    private val requiredPermissionsMap = mapOf<String, Int>(
        Manifest.permission.POST_NOTIFICATIONS to Build.VERSION_CODES.TIRAMISU,
    )

    private val remindersHelper by lazy {
        RemindersHelper(requireActivity()) { hour, minute, status ->
            this@AddTaskFragment.hour = hour
            this@AddTaskFragment.minute = minute
            reminderStatus = status
            setContent(reminderStatus, hour, minute)
        }
    }

    private val permissionsHelper =
        PermissionsHelper(this, requiredPermissionsMap) {
            remindersHelper.showReminderAlertDialog()
        }

    private var reminderStatus: TaskReminderStatus = TaskReminderStatus.NONE
    private var hour: Int = 0
    private var minute: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity()

        setContent()
        setListeners(activity)
    }

    private fun setContent(
        reminderStatus: TaskReminderStatus? = null,
        reminderHour: Int? = null,
        reminderMinute: Int? = null
    ) {
        if (reminderStatus == null) {
            binding.apply {
                textReminderStatus.text = TaskReminderStatus.NONE.name
                textReminderTime.text = TaskReminderStatus.NONE.name
            }
        } else {
            val reminderTime = "$reminderHour:$reminderMinute"
            binding.apply {
                textReminderStatus.text = reminderStatus.name
                textReminderTime.text = reminderTime
            }
        }
    }

    private fun setListeners(activity: FragmentActivity) {
        binding.apply {
            setReminder.setOnClickListener {
                permissionsHelper.checkIfPermissionsGranted() // Has callback defined above
            }

            saveTask.setOnClickListener {
                val title = fieldTitle.editText?.text.toString()
                if (title.isNotEmpty()) {
                    val taskEntity = TaskEntity(null , title, TaskStatus.IN_PROGRESS, reminderStatus, hour, minute)
                    viewModel.insertTask(taskEntity)
                    navigateToPreviousFragment(activity)
                } else {
                    Toast.makeText(context, "Enter title", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun navigateToPreviousFragment(activity: FragmentActivity) {
        val fragmentManager = activity.supportFragmentManager
        fragmentManager.popBackStack()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}