package com.serhiitymoshenko.organizer.ui.home.todo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.serhiitymoshenko.organizer.R
import com.serhiitymoshenko.organizer.data.models.task.Task
import com.serhiitymoshenko.organizer.databinding.FragmentTodoHomeBinding
import com.serhiitymoshenko.organizer.ui.home.todo.adapters.TabsAdapter
import com.serhiitymoshenko.organizer.ui.home.todo.childfragments.tasks.adapters.TasksAdapter
import com.serhiitymoshenko.organizer.ui.home.todo.childfragments.addtask.AddTaskFragment
import com.serhiitymoshenko.organizer.ui.home.todo.childfragments.edittask.EditTaskFragment
import com.serhiitymoshenko.organizer.ui.home.todo.viewmodel.TodoHomeViewModel
import com.serhiitymoshenko.organizer.utils.helpers.AlarmManagerHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class TodoHomeFragment : Fragment() {

    private var _binding: FragmentTodoHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by inject<TodoHomeViewModel>()

    private lateinit var tabsAdapter: TabsAdapter
    private lateinit var searchedTasksAdapter: TasksAdapter

    private val alarmManager by lazy {
        AlarmManagerHelper(requireContext())
    }

    private val tabNames by lazy {
        listOf(
            getString(R.string.in_progress_title),
            getString(R.string.done_title),
            getString(R.string.deleted_title)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity()

        setupPager(activity)
        setupSearchedTasksRecycler(activity)
        setListeners(activity)
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launch(Dispatchers.IO + SupervisorJob()) {
            viewModel.getSearchedTasks().collect { tasks ->
                searchedTasksAdapter.submitList(tasks)
            }
        }
        lifecycleScope.launch(Dispatchers.IO + SupervisorJob()) {
            viewModel.getTasksWithReminder().collect { tasks ->
                alarmManager.cancel()
                alarmManager.schedule(tasks)
            }
        }
    }

    private fun setListeners(activity: FragmentActivity) {
        val searchView = binding.tasksSearchView
        searchView.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) = viewModel.setSearchQuery(s.toString())
        })

        binding.addTask.setOnClickListener {
            navigateToAddTaskFragment(activity)
        }
    }

    private fun setupSearchedTasksRecycler(activity: FragmentActivity) {
        initAdapter(activity)

        val searchRecycler = binding.searchedTasksRecycler
        searchRecycler.apply {
            adapter = searchedTasksAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun initAdapter(activity: FragmentActivity) {
        searchedTasksAdapter = TasksAdapter { task ->
            navigateToEditTaskFragment(activity, task)
        }
    }

    private fun navigateToAddTaskFragment(activity: FragmentActivity) {
        val addTaskFragment = AddTaskFragment()

        val organizerContainerId = R.id.home_container
        val fragmentManager = activity.supportFragmentManager
        fragmentManager.commit {
            replace(organizerContainerId, addTaskFragment)
            addToBackStack(null)
        }
    }

    private fun navigateToEditTaskFragment(activity: FragmentActivity, task: Task) {
        val editTaskFragment = EditTaskFragment.newInstance(task)

        val organizerContainerId = R.id.home_container
        val fragmentManager = activity.supportFragmentManager
        fragmentManager.commit {
            replace(organizerContainerId, editTaskFragment)
            addToBackStack(null)
        }
    }

    private fun setupPager(activity: FragmentActivity) {
        val tabLayout = binding.tabLayout
        val tabsPager = binding.tabsPager

        setTabAdapter(activity, tabsPager)
        setupTabLayoutMediator(tabLayout, tabsPager)
    }

    private fun setupTabLayoutMediator(tabLayout: TabLayout, tabsPager: ViewPager2) {
        TabLayoutMediator(tabLayout, tabsPager) { tab, position ->
            tab.text = tabNames[position]
        }.attach()
    }

    private fun setTabAdapter(activity: FragmentActivity, tabsPager: ViewPager2) {
        tabsAdapter = TabsAdapter(activity, tabNames.size)
        tabsPager.adapter = tabsAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}