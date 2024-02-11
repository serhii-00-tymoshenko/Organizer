package com.serhiitymoshenko.contacts.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.serhiitymoshenko.organizer.data.models.Contact
import com.serhiitymoshenko.organizer.databinding.ItemContactCompactBinding

class RecentlyAddedContactsAdapter :
    ListAdapter<Contact, RecentlyAddedContactsAdapter.RecentlyAddedContactViewHolder>(
        DIFF_CALLBACK
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentlyAddedContactViewHolder {
        val binding =
            ItemContactCompactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentlyAddedContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentlyAddedContactViewHolder, position: Int) {
        val currentContact = getItem(position)
        holder.bind(currentContact)
    }

    inner class RecentlyAddedContactViewHolder(private val binding: ItemContactCompactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact) {
            setContent(contact)
            setListeners(contact)
        }

        private fun setContent(contact: Contact) {
            binding.apply {
                textFirstNameCompact.text = contact.firstName
                photoCompact.load(contact.photo)
            }
        }

        private fun setListeners(contact: Contact) {

        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Contact>() {
            override fun areItemsTheSame(oldItem: Contact, newItem: Contact) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Contact, newItem: Contact) =
                oldItem == newItem
        }
    }
}