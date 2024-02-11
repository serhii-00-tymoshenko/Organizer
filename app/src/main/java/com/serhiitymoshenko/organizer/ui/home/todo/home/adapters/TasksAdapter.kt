package com.serhiitymoshenko.organizer.ui.home.todo.home.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.serhiitymoshenko.organizer.data.models.Task
import com.serhiitymoshenko.organizer.data.models.TaskReminderStatus
import com.serhiitymoshenko.organizer.data.models.TaskStatus
import com.serhiitymoshenko.organizer.databinding.ItemTaskBinding

class TasksAdapter(
    private val callback: (Task) -> Unit
) : ListAdapter<Task, TasksAdapter.TaskViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = getItem(position)
        holder.bind(currentTask)
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            setCallback(task)
            setContent(task)
        }

        private fun setCallback(task: Task) {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                binding.root.apply {
                    setOnClickListener {
                        callback.invoke(task)
                    }
                }
            }
        }

        private fun setContent(task: Task) {
            binding.apply {
                taskName.text = task.title

                if (task.status == TaskStatus.DONE) {
                    imageDone.visibility = View.VISIBLE
                }

                if (task.status == TaskStatus.DELETED) {
                    taskName.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }

                if (task.reminderStatus != TaskReminderStatus.NONE) {
                    imageScheduled.visibility = View.VISIBLE
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(oldItem: Task, newItem: Task) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Task, newItem: Task) =
                oldItem == newItem
        }
    }
}