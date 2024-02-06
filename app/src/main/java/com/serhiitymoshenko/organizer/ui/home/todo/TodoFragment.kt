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
import com.serhiitymoshenko.organizer.data.models.Task
import com.serhiitymoshenko.organizer.databinding.FragmentTodoBinding
import com.serhiitymoshenko.organizer.ui.home.todo.adapters.TabsAdapter
import com.serhiitymoshenko.organizer.ui.home.todo.adapters.TasksAdapter
import com.serhiitymoshenko.organizer.ui.home.todo.edittask.EditTaskFragment
import com.serhiitymoshenko.organizer.ui.home.todo.viewmodel.TodoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class TodoFragment : Fragment() {

    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!

    private val viewModel by inject<TodoViewModel>()

    private lateinit var tabsAdapter: TabsAdapter
    private lateinit var searchedTasksAdapter: TasksAdapter

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
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity()

        setupPager(activity)
        setupSearchedTasksRecycler(activity)
        setListeners()
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launch(Dispatchers.IO + SupervisorJob()) {
            viewModel.getSearchedTasks().collect { tasks ->
                searchedTasksAdapter.submitList(tasks)
            }
        }
    }

    private fun setListeners() {
        val searchView = binding.tasksSearchView
        searchView.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) = viewModel.setSearchQuery(s.toString())
        })
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

//    private fun navigateToAddTaskFragment(activity: FragmentActivity) {
//        val addTaskFragment = AddTaskFragment()
//
//        val organizerContainerId = R.id.organizer_container
//        val fragmentManager = activity.supportFragmentManager
//        fragmentManager.commit {
//            replace(organizerContainerId, addTaskFragment)
//            addToBackStack(null)
//        }
//    }

    private fun navigateToEditTaskFragment(activity: FragmentActivity, task: Task) {
        val editTaskFragment = EditTaskFragment.newInstance(task)

        val organizerContainerId = R.id.organizer_container
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