package com.serhiitymoshenko.organizer.ui.home.todo.edittask

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.serhiitymoshenko.organizer.data.models.Task
import com.serhiitymoshenko.organizer.data.models.TaskReminderStatus
import com.serhiitymoshenko.organizer.data.models.TaskStatus
import com.serhiitymoshenko.organizer.databinding.FragmentEditTaskBinding
import com.serhiitymoshenko.organizer.ui.home.todo.edittask.viewmodel.EditTaskViewModel
import org.koin.android.ext.android.inject


class EditTaskFragment : Fragment() {

    private var _binding: FragmentEditTaskBinding? = null
    private val binding get() = _binding!!

    private val task by lazy {
        arguments?.getParcelable(EDIT_TASK_TASK_PARAM_KEY) ?: Task(
            "Not found",
            TaskStatus.DELETED,
            TaskReminderStatus.NONE,
            0
        )
    }

    private val viewModel by inject<EditTaskViewModel>()

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // TODO
            } else {
                // TODO
            }
        }

    private lateinit var newStatus: TaskStatus
    private var isImportantTask: Boolean = false

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

        setInitialData()
        setContent()
        setListeners(activity)
    }

    private fun setInitialData() {
    }

    private fun setListeners(activity: FragmentActivity) {

    }

    private fun returnToPreviousFragment(activity: FragmentActivity) {
        val fragmentManager = activity.supportFragmentManager
        fragmentManager.popBackStack()
    }

    private fun setContent() {
    }

    private fun requestNotificationsPermission(activity: FragmentActivity) {
        when {
            ActivityCompat.shouldShowRequestPermissionRationale(
                activity, Manifest.permission.POST_NOTIFICATIONS
            ) -> {
                Toast.makeText(
                    context,
                    "We need this permission if you want to receive notifications",
                    Toast.LENGTH_LONG
                ).show()
                // TODO: showInContextUI(...
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val EDIT_TASK_TASK_PARAM_KEY = "edit_task_task_param_key"

        @JvmStatic
        fun newInstance(task: Task) = EditTaskFragment().apply {
            arguments = Bundle().apply {
                putParcelable(EDIT_TASK_TASK_PARAM_KEY, task)
            }
        }
    }
}