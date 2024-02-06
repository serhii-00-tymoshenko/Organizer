package com.serhiitymoshenko.organizer.ui.auth

import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.serhiitymoshenko.organizer.BuildConfig
import com.serhiitymoshenko.organizer.R
import com.serhiitymoshenko.organizer.databinding.FragmentAuthBinding
import com.serhiitymoshenko.organizer.ui.home.HomeFragment
import com.serhiitymoshenko.organizer.utils.TAG
import org.koin.android.ext.android.inject

class AuthFragment : Fragment() {
    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    private val firebaseAuth: FirebaseAuth by inject<FirebaseAuth>()

    private val oneTapClient by lazy {
        Identity.getSignInClient(requireContext())
    }

    private val signInRequest by lazy {
        BeginSignInRequest.builder()
            .setPasswordRequestOptions(
                BeginSignInRequest.PasswordRequestOptions.builder()
                    .setSupported(true)
                    .build()
            )
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(BuildConfig.WEB_CLIENT_ID)
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()
    }

    private val intentSenderLauncher =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            val credentials = oneTapClient.getSignInCredentialFromIntent(result.data)
            val idToken = credentials.googleIdToken

            if (idToken != null) {
                val firebaseCredentials = GoogleAuthProvider.getCredential(idToken, null)
                firebaseSignIn(firebaseCredentials, requireActivity())
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity()

        checkIfUserExists(activity)

        binding.root.setOnClickListener {
            signIn(activity)
        }
    }

    private fun checkIfUserExists(activity: FragmentActivity) {
        val currentUser = firebaseAuth.currentUser

        if (currentUser != null) {
            openOrganizerFragment(activity)
        } else {
            signIn(activity)
        }
    }

    private fun openOrganizerFragment(activity: FragmentActivity) {
        val mainContainerId = R.id.main_container
        val homeFragment = HomeFragment()

        val fragmentManager = activity.supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(mainContainerId, homeFragment)
            .commit()
    }

    private fun firebaseSignIn(credentials: AuthCredential, activity: FragmentActivity) {
        Log.d(TAG, credentials.toString())
        firebaseAuth.signInWithCredential(credentials)
            .addOnSuccessListener {
                openOrganizerFragment(activity)
            }
    }

    private fun signIn(activity: FragmentActivity) {
        oneTapClient.beginSignIn(signInRequest)
            .addOnSuccessListener(activity) { result ->
                try {
                    val senderIntent = result.pendingIntent.intentSender
                    val senderRequestIntent = IntentSenderRequest.Builder(senderIntent).build()
                    intentSenderLauncher.launch(senderRequestIntent)
                } catch (e: IntentSender.SendIntentException) {
                    Log.e(TAG, "Couldn't start One Tap UI: ${e.localizedMessage}")
                }
            }
            .addOnFailureListener(activity) { e ->
                Log.d(TAG, e.localizedMessage)
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}