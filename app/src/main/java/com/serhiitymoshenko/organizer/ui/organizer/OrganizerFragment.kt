package com.serhiitymoshenko.organizer.ui.organizer

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import com.serhiitymoshenko.organizer.R
import com.serhiitymoshenko.organizer.databinding.FragmentOrganizerBinding

class OrganizerFragment : Fragment() {
    private var _binding: FragmentOrganizerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrganizerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()

        setupNavigation(context)
    }

    private fun setupNavigation(context: Context) {
        NavigationBarView.OnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.todo -> {
                    Toast.makeText(context, "Todo", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.notes -> {
                    Toast.makeText(context, "Todo", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.contacts -> {
                    Toast.makeText(context, "Todo", Toast.LENGTH_SHORT).show()
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}