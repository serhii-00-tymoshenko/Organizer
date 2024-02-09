package com.serhiitymoshenko.organizer.ui.home.todo.childfragments.edittask

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.serhiitymoshenko.organizer.data.models.converters.toTaskEntity
import com.serhiitymoshenko.organizer.data.models.task.Task
import com.serhiitymoshenko.organizer.data.models.task.TaskReminderStatus
import com.serhiitymoshenko.organizer.data.models.task.TaskStatus
import com.serhiitymoshenko.organizer.databinding.FragmentEditTaskBinding
import com.serhiitymoshenko.organizer.ui.home.todo.childfragments.edittask.viewmodel.EditTaskViewModel
import com.serhiitymoshenko.organizer.utils.helpers.PermissionsHelper
import com.serhiitymoshenko.organizer.utils.helpers.RemindersHelper
import org.koin.android.ext.android.inject


class EditTaskFragment : Fragment() {

    private var _binding: FragmentEditTaskBinding? = null
    private val binding get() = _binding!!

    private val task by lazy {
        arguments?.getParcelable(TASK_PARAM_KEY) ?: Task(
            0,
            "Not found",
            TaskStatus.DELETED,
            TaskReminderStatus.NONE,
            null,
            null
        )
    }

    private val requiredPermissionsMap = mapOf<String, Int>(
        Manifest.permission.POST_NOTIFICATIONS to Build.VERSION_CODES.TIRAMISU,
    )

    private val remindersHelper by lazy {
        RemindersHelper(requireActivity()) { hour, minute, status ->
            this@EditTaskFragment.hour = hour
            this@EditTaskFragment.minute = minute
            reminderStatus = status
            changeReminderStatus(status, hour, minute)
        }
    }

    private val permissionsHelper =
        PermissionsHelper(this, requiredPermissionsMap) {
            remindersHelper.showReminderAlertDialog()
        }

    private val viewModel by inject<EditTaskViewModel>()

    private var reminderStatus: TaskReminderStatus? = null
    private var hour: Int? = null
    private var minute: Int? = null

    private lateinit var newStatus: TaskStatus

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity()

        setContent()
        setListeners(activity)
    }

    private fun setCheckedRadioButton() {
        when (task.status) {
            TaskStatus.IN_PROGRESS -> binding.radioInProgress.isChecked = true
            TaskStatus.DONE -> binding.radioDone.isChecked = true
            TaskStatus.DELETED -> binding.radioDeleted.isChecked = true
        }
    }

    private fun setListeners(activity: FragmentActivity) {
        binding.apply {
            saveTask.setOnClickListener {
                val title = fieldTitle.editText?.text.toString()

                if (title.isNotEmpty()) {
                    val updatedTask = task.copy(
                        title = title,
                        status = newStatus,
                        reminderStatus = reminderStatus ?: TaskReminderStatus.NONE,
                        reminderHour = hour,
                        reminderMinute = minute
                    )
                    viewModel.updateTask(updatedTask.toTaskEntity())
                    returnToPreviousFragment(activity)
                } else {
                    Toast.makeText(activity, "Enter title", Toast.LENGTH_SHORT).show()
                }
            }

            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                newStatus = when (checkedId) {
                    radioInProgress.id -> TaskStatus.IN_PROGRESS
                    radioDone.id -> TaskStatus.DONE
                    radioDeleted.id -> TaskStatus.DELETED
                    else -> task.status
                }
            }

            setReminder.setOnClickListener {
                permissionsHelper.checkIfPermissionsGranted()
            }
        }
    }

    private fun returnToPreviousFragment(activity: FragmentActivity) {
        val fragmentManager = activity.supportFragmentManager
        fragmentManager.popBackStack()
    }

    private fun setContent() {
        setCheckedRadioButton()
        newStatus = task.status

        binding.apply {
            fieldTitle.editText?.setText(task.title)
            textReminderStatus.text = task.reminderStatus.name

            if (task.reminderStatus != TaskReminderStatus.NONE) {
                val reminderTime = "${task.reminderHour}:${task.reminderMinute}"
                textReminderTime.text = reminderTime
            } else {
                textReminderTime.text = TaskReminderStatus.NONE.name
            }
        }
    }

    private fun changeReminderStatus(reminderStatus: TaskReminderStatus, hour: Int, minute: Int) {
        val reminderTime = "$hour:$minute"
        binding.apply {
            textReminderStatus.text = reminderStatus.name
            textReminderTime.text = reminderTime
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val TASK_PARAM_KEY = "task_param_key"

        @JvmStatic
        fun newInstance(task: Task) = EditTaskFragment().apply {
            arguments = Bundle().apply {
                putParcelable(TASK_PARAM_KEY, task)
            }
        }
    }
}