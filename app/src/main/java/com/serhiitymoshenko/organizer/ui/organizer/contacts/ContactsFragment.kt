package com.serhiitymoshenko.organizer.ui.organizer.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material.MaterialTheme
import androidx.fragment.app.Fragment
import com.serhiitymoshenko.organizer.databinding.FragmentContactsBinding
import com.serhiitymoshenko.organizer.ui.organizer.contacts.compose.Contacts

class ContactsFragment : Fragment() {
    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!

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
        Toast.makeText(requireContext(), "Fragment recreated", Toast.LENGTH_SHORT).show()
        setComposable()
    }

    private fun setComposable() {
        binding.composeView.setContent {
            MaterialTheme {
                Contacts()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}