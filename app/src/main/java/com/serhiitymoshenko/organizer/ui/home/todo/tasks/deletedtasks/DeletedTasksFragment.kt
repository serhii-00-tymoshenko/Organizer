package com.serhiitymoshenko.organizer.ui.home.todo.tasks.deletedtasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.serhiitymoshenko.organizer.databinding.FragmentDeletedTasksBinding
import com.serhiitymoshenko.organizer.ui.home.todo.adapters.TasksAdapter

class DeletedTasksFragment : Fragment() {
    private var _binding: FragmentDeletedTasksBinding? = null
    private val binding get() = _binding!!

    private val tasksAdapter by lazy {
        TasksAdapter {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeletedTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()
    }

    private fun setupRecycler() {
        val recycler = binding.tasks.tasksRecycler
        recycler.apply {
            adapter = tasksAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }
}