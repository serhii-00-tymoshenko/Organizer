package com.serhiitymoshenko.organizer.ui.home.contacts.children.editcontact

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import coil.load
import coil.size.Scale
import com.serhiitymoshenko.organizer.data.models.contact.Contact
import com.serhiitymoshenko.organizer.data.models.converters.toContactEntity
import com.serhiitymoshenko.organizer.databinding.FragmentAddContactBinding
import com.serhiitymoshenko.organizer.ui.home.contacts.children.addcontact.viewmodel.AddContactViewModel
import com.serhiitymoshenko.organizer.utils.resize
import org.koin.android.ext.android.inject

class EditContactFragment : Fragment() {
    private var _binding: FragmentAddContactBinding? = null
    private val binding get() = _binding!!

    private val viewModel by inject<AddContactViewModel>()

    private val pickPhotoLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            photoUri = uri
            binding.photo.load(uri) {
                scale(Scale.FILL)
            }
        }

    private var photoUri: Uri? = null

    private val contactEntity by lazy {
        arguments?.getParcelable(CONTACT_PARAM_KEY) ?: Contact(
            0,
            "Not found",
            "Not found",
            "Not found",
            isNew = false
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity()

        setContent()
        setListeners(activity)
    }

    private fun setContent() {
        binding.apply {
            fieldPhoneNumber.editText?.setText(contactEntity.phoneNumber)
            fieldFirstName.editText?.setText(contactEntity.firstName)
            fieldLastName.editText?.setText(contactEntity.lastName)
            fieldEmail.editText?.setText(contactEntity.email)

            photo.load(contactEntity.photo)
        }
    }

    private fun setListeners(activity: FragmentActivity) {
        binding.apply {
            saveContact.setOnClickListener {
                val firstName = fieldFirstName.editText?.text.toString()
                val lastName = fieldLastName.editText?.text.toString()
                val phoneNumber = fieldPhoneNumber.editText?.text.toString()
                val email = fieldEmail.editText?.text.toString()
                var photo: Bitmap? = null ?: contactEntity.photo

                photoUri?.let { uri ->
                    val contentResolver = activity.contentResolver

                    photo = if (Build.VERSION.SDK_INT < 28) {
                        MediaStore.Images.Media.getBitmap(contentResolver, uri)
                    } else {
                        val source = ImageDecoder.createSource(contentResolver, uri)
                        ImageDecoder.decodeBitmap(source)
                    }
                    photo = photo?.resize(180)
                }

                if (firstName.isNotEmpty() && lastName.isNotEmpty() && phoneNumber.isNotEmpty()) {

                    val contact = Contact(0, firstName, lastName, phoneNumber, email, photo, true)
                    viewModel.insertContact(contact.toContactEntity())
                } else {
                    Toast.makeText(activity, "Enter name and phone number", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            photoContainer.setOnClickListener {
                pickPhotoLauncher.launch("image/*")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val CONTACT_PARAM_KEY = "contact"

        @JvmStatic
        fun newInstance(contact: Contact) =
            EditContactFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(CONTACT_PARAM_KEY, contact)
                }
            }
    }
}