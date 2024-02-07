package com.serhiitymoshenko.organizer.ui.home.contacts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.serhiitymoshenko.contacts.ui.home.adapters.MyContactsAdapter
import com.serhiitymoshenko.contacts.ui.home.adapters.RecentlyAddedContactsAdapter
import com.serhiitymoshenko.organizer.R
import com.serhiitymoshenko.organizer.ui.home.contacts.viewmodel.ContactsViewModel
import com.serhiitymoshenko.organizer.data.models.Contact
import com.serhiitymoshenko.organizer.databinding.FragmentContactsBinding
import com.serhiitymoshenko.organizer.ui.home.contacts.addcontact.AddContactFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ContactsFragment : Fragment() {

    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ContactsViewModel by inject<ContactsViewModel>()

    private lateinit var myContactsAdapter: MyContactsAdapter
    private lateinit var recentlyAddedContactsAdapter: RecentlyAddedContactsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity()

        setupRecyclers(activity)
        initObservers()
        setListeners(activity)
    }

    private fun setListeners(activity: FragmentActivity) {
        binding.addContact.setOnClickListener {
            navigateToAddContactFragment(activity)
        }
    }

    private fun navigateToAddContactFragment(activity: FragmentActivity) {
        val addContactFragment = AddContactFragment()

        val homeContainerId = R.id.home_container
        val fragmentManager = activity.supportFragmentManager
        fragmentManager.commit {
            replace(homeContainerId, addContactFragment)
            addToBackStack(null)
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.getContacts().collect { contacts: List<Contact> ->
                myContactsAdapter.submitList(contacts)
            }
        }
        lifecycleScope.launch {
            viewModel.getRecentlyAddedContacts().collect { contacts: List<Contact> ->
                recentlyAddedContactsAdapter.submitList(contacts)
            }
        }
    }

    private fun setupRecyclers(activity: FragmentActivity) {
        setupRecentlyAddedContactsRecycler(activity)
        setupMyContactsRecycler(activity)
    }

    private fun setupMyContactsRecycler(activity: FragmentActivity) {
        myContactsAdapter = MyContactsAdapter()
        val recycler = binding.recyclerMyContacts

        recycler.apply {
            adapter = myContactsAdapter
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }
    }

    private fun setupRecentlyAddedContactsRecycler(activity: FragmentActivity) {
        recentlyAddedContactsAdapter = RecentlyAddedContactsAdapter()
        val recycler = binding.recyclerRecentlyAddedContacts

        recycler.apply {
            adapter = recentlyAddedContactsAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val BACK_STACK_NAME = "contacts"
    }
}