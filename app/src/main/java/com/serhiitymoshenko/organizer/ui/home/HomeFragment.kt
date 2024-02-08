package com.serhiitymoshenko.organizer.ui.home

import android.Manifest
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.serhiitymoshenko.organizer.R
import com.serhiitymoshenko.organizer.databinding.FragmentHomeBinding
import com.serhiitymoshenko.organizer.ui.home.contacts.ContactsFragment
import com.serhiitymoshenko.organizer.ui.home.todo.TodoFragment
import com.serhiitymoshenko.organizer.ui.home.viewmodel.HomeViewModel
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by inject<HomeViewModel>()

    private val requestPermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {

        }

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
        initObservers(activity)
    }

    private fun setContent(activity: FragmentActivity) {
        val todoFragment = TodoFragment()
        openNavSelectedFragment(activity, todoFragment)
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
        val homeContainerId = binding.homeContainer.id
        val fragmentManager = activity.supportFragmentManager

        fragmentManager.commit {
            replace(homeContainerId, fragment)
        }
    }

    private fun initObservers(activity: FragmentActivity) {
        viewModel.getIsFistLaunch().observe(activity) {
            checkIfFirstLaunch(activity, it)
        }
    }

    private fun checkIfFirstLaunch(context: Context, isFirstLaunch: Boolean) {
        if (isFirstLaunch) {
            startPermissionAwareDialog(context)
        }
    }

    private fun startPermissionAwareDialog(context: Context) {
        val resources = context.resources
        val title = resources.getString(R.string.permission_aware_dialog_title)
        val message = resources.getString(R.string.permission_aware_dialog_message)
        val neutralButtonText = resources.getString(R.string.dialog_cancel_text)
        val positiveButtonText = resources.getString(R.string.dialog_continue_text)

        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveButtonText) { dialog, _ ->
                requestPermissions()
                viewModel.changeIsFirstLaunchToFalse()
                dialog.cancel()
            }
            .setNeutralButton(neutralButtonText) { dialog, _ ->
                viewModel.changeIsFirstLaunchToFalse()
                dialog.cancel()
            }.show()
    }


    private fun requestPermissions() {
        val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arrayOf(
                Manifest.permission.POST_NOTIFICATIONS
            )
        } else {
            arrayOf()
        }
        requestPermissionsLauncher.launch(permissions)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}