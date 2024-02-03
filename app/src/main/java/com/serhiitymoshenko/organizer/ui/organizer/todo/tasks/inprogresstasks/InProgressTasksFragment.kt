package com.serhiitymoshenko.organizer.ui.organizer.todo.tasks.inprogresstasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.serhiitymoshenko.organizer.R
import com.serhiitymoshenko.organizer.data.entities.task.Task
import com.serhiitymoshenko.organizer.databinding.FragmentInProgressTasksBinding
import com.serhiitymoshenko.organizer.ui.organizer.todo.tasks.adapters.TasksAdapter
import com.serhiitymoshenko.organizer.ui.organizer.todo.tasks.inprogresstasks.viewmodel.InProgressTasksViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class InProgressTasksFragment : Fragment() {
    private var _binding: FragmentInProgressTasksBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<InProgressTasksViewModel>()
    private val tasksAdapter by lazy {
        TasksAdapter { task ->
            openEditTaskFragment(requireActivity(), task)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInProgressTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()
        initObservers()
    }

    private fun setupRecycler() {
        val recycler = binding.tasks.tasksRecycler
        recycler.apply {
            adapter = tasksAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun openEditTaskFragment(activity: FragmentActivity, task: Task) {
        val mainFragmentId = R.id.main_container

    }

    private fun initObservers() {
        lifecycleScope.launch(Dispatchers.IO + SupervisorJob()) {
            viewModel.getInProgressTasks().collect { tasks ->
                tasksAdapter.submitList(tasks)
            }
        }
    }
}