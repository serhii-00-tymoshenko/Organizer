package com.serhiitymoshenko.organizer.ui.home.todo.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.serhiitymoshenko.organizer.ui.home.todo.children.tasks.deletedtasks.DeletedTasksFragment
import com.serhiitymoshenko.organizer.ui.home.todo.children.tasks.donetasks.DoneTasksFragment
import com.serhiitymoshenko.organizer.ui.home.todo.children.tasks.inprogresstasks.InProgressTasksFragment

class TabsAdapter(fragmentActivity: FragmentActivity, private val size: Int) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> InProgressTasksFragment()
            1 -> DoneTasksFragment()
            2 -> DeletedTasksFragment()
            else -> throw IllegalArgumentException("Fragment not found")
        }
    }
}