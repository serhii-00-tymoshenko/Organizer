package com.serhiitymoshenko.organizer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.serhiitymoshenko.organizer.R
import com.serhiitymoshenko.organizer.databinding.FragmentHomeBinding
import com.serhiitymoshenko.organizer.ui.home.contacts.ContactsFragment
import com.serhiitymoshenko.organizer.ui.home.todo.TodoFragment

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity()

        setupNavigation(activity)
    }

    private fun setupNavigation(activity: FragmentActivity) {
        binding.navigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.todo -> {
                    val todoFragment = TodoFragment()
                    openNavSelectedFragment(activity, todoFragment)
                    true
                }

                R.id.notes -> {
                    Toast.makeText(context, "Todo", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.contacts -> {
                    val contactsFragment = ContactsFragment()
                    openNavSelectedFragment(activity, contactsFragment)
                    true
                }

                R.id.profile -> {
                    Toast.makeText(context, "Todo", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }
    }

    private fun openNavSelectedFragment(
        activity: FragmentActivity,
        fragment: Fragment,
    ) {
        val organizerContainerId = binding.organizerContainer.id
        val fragmentManager = activity.supportFragmentManager

        fragmentManager.commit {
            replace(organizerContainerId, fragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}