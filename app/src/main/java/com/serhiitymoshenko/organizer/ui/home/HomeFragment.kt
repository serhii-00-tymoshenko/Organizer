package com.serhiitymoshenko.organizer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.serhiitymoshenko.organizer.R
import com.serhiitymoshenko.organizer.databinding.FragmentHomeBinding
import com.serhiitymoshenko.organizer.ui.home.contacts.ContactsHomeFragment
import com.serhiitymoshenko.organizer.ui.home.todo.TodoHomeFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

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
        setContent(activity)
    }

    private fun setContent(activity: FragmentActivity) {
        val todoHomeFragment = TodoHomeFragment()
        openNavSelectedFragment(activity, todoHomeFragment)
    }

    private fun setupNavigation(activity: FragmentActivity) {
        binding.navigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.todo -> {
                    val todoHomeFragment = TodoHomeFragment()
                    openNavSelectedFragment(activity, todoHomeFragment)
                    true
                }

                R.id.notes -> {
                    Toast.makeText(context, "Todo", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.contacts -> {
                    val contactsHomeFragment = ContactsHomeFragment()
                    openNavSelectedFragment(activity, contactsHomeFragment)
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
        val homeContainerId = binding.homeContainer.id
        val fragmentManager = activity.supportFragmentManager

        fragmentManager.commit {
            replace(homeContainerId, fragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}