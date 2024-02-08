package com.serhiitymoshenko.organizer.ui.home.todo.addtask

import android.Manifest
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.serhiitymoshenko.organizer.data.models.Task
import com.serhiitymoshenko.organizer.data.models.TaskReminderStatus
import com.serhiitymoshenko.organizer.data.models.TaskStatus
import com.serhiitymoshenko.organizer.databinding.FragmentAddTaskBinding
import com.serhiitymoshenko.organizer.ui.home.todo.addtask.viewmodel.AddTaskViewModel
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
            _hour = hour
            _minute = minute
            _reminderStatus = status
        }
    }

    private val permissionsHelper =
        PermissionsHelper(this, requiredPermissionsMap) {
            remindersHelper.showReminderAlertDialog()
        }

    private var _reminderStatus: TaskReminderStatus = TaskReminderStatus.NONE
    private var _hour: Int = 0
    private var _minute: Int = 0

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

        setListeners(activity)
    }

    private fun setListeners(activity: FragmentActivity) {
        binding.apply {
            setReminder.setOnClickListener {
                permissionsHelper.checkIfPermissionsGranted() // Has callback defined above
            }

            save.setOnClickListener {
                val title = fieldTitle.editText?.text.toString()
                if (title.isNotEmpty()) {
                    val task = Task(title, TaskStatus.IN_PROGRESS, _reminderStatus, _hour, _minute)
                    viewModel.insertTask(task)
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