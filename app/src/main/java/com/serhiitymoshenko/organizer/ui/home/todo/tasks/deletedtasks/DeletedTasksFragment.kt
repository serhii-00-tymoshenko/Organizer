package com.serhiitymoshenko.organizer.ui.home.todo.tasks.deletedtasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.serhiitymoshenko.organizer.R
import com.serhiitymoshenko.organizer.data.models.Task
import com.serhiitymoshenko.organizer.databinding.FragmentDeletedTasksBinding
import com.serhiitymoshenko.organizer.ui.home.todo.edittask.EditTaskFragment
import com.serhiitymoshenko.organizer.ui.home.todo.home.adapters.TasksAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeletedTasksFragment : Fragment() {

    private var _binding: FragmentDeletedTasksBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<DeletedTasksViewModel>()

    private lateinit var tasksAdapter: TasksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeletedTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity()

        setupRecycler(activity)
        initObservers()
        setListeners()
    }

    private fun setListeners() {
        binding.apply {
            deleteForever.setOnClickListener {
                viewModel.deleteAllTasks()
            }
        }
    }

    private fun setupRecycler(activity: FragmentActivity) {
        initAdapter(activity)

        val recycler = binding.tasks.tasksRecycler
        recycler.apply {
            adapter = tasksAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun initAdapter(activity: FragmentActivity) {
        tasksAdapter = TasksAdapter { task ->
            openEditTaskFragment(activity, task)
        }
    }

    private fun openEditTaskFragment(activity: FragmentActivity, task: Task) {
        val editTaskFragment = EditTaskFragment.newInstance(task)

        val organizerContainerId = R.id.home_container

        val fragmentManager = activity.supportFragmentManager
        fragmentManager.commit {
            replace(organizerContainerId, editTaskFragment)
            addToBackStack(null)
        }
    }

    private fun initObservers() {
        lifecycleScope.launch(Dispatchers.IO + SupervisorJob()) {
            viewModel.getDeletedTasks().collect { tasks ->
                tasksAdapter.submitList(tasks)
            }
        }
    }
}