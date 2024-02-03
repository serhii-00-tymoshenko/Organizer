package com.serhiitymoshenko.organizer.ui.organizer.todo.tasks.donetasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.serhiitymoshenko.organizer.databinding.FragmentDoneTasksBinding
import com.serhiitymoshenko.organizer.ui.organizer.todo.tasks.adapters.TasksAdapter

class DoneTasksFragment : Fragment() {
    private var _binding: FragmentDoneTasksBinding? = null
    private val binding get() = _binding!!

    private val tasksAdapter by lazy {
        TasksAdapter {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoneTasksBinding.inflate(inflater, container, false)
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