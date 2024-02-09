package com.serhiitymoshenko.contacts.ui.home.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.serhiitymoshenko.organizer.data.models.contact.Contact
import com.serhiitymoshenko.organizer.databinding.ItemContactBinding

class MyContactsAdapter :
    ListAdapter<Contact, MyContactsAdapter.MyContactViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyContactViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyContactViewHolder, position: Int) {
        val currentContact = getItem(position)
        holder.bind(currentContact)
    }

    inner class MyContactViewHolder(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact) {
            setContent(contact)
            setListeners(contact)
        }

        private fun setContent(contact: Contact) {
            binding.apply {
                textFirstName.text = contact.firstName
                textLastName.text = contact.lastName
                photo.load(contact.photo)
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