package com.serhiitymoshenko.organizer.utils.helpers

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.serhiitymoshenko.organizer.R

class PermissionsHelper(private val fragment: Fragment, private val permissions: Map<String, Int>, private val permissionsGrantedCallback: () -> Unit) {

    private val requestPermissionsLauncher =
        fragment.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissionsMap ->
            if (permissionsMap.values.any { isGranted -> isGranted }) {
                permissionsGrantedCallback.invoke()
            } else {
                requestPermissions()
            }
        }

    private val requestPermissionLauncher =
        fragment.registerForActivityResult(ActivityResultContracts.RequestPermission()) { }

    fun checkIfPermissionsGranted() {
        val isAllPermissionsGranted = permissions.keys.any { permission ->
            ContextCompat.checkSelfPermission(
                fragment.requireContext(),
                permission
            ) == PackageManager.PERMISSION_GRANTED
        }

        if (isAllPermissionsGranted) {
            permissionsGrantedCallback.invoke()
        } else {
            requestPermissions()
        }
    }

    private fun requestPermissions() {
        when {
            permissions.keys.any { permissionName ->
                !ActivityCompat.shouldShowRequestPermissionRationale(
                    fragment.requireActivity(), permissionName)
            } -> startPermissionAwareDialog(fragment.requireContext(), permissions)

            else -> {
                requestPermissionsLauncher.launch(permissions.keys.toTypedArray())
            }
        }
    }

    private fun startPermissionAwareDialog(context: Context, permissions: Map<String, Int>) {
        val resources = context.resources
        val title = resources.getString(R.string.permission_aware_dialog_title)
        val message = resources.getString(R.string.permission_aware_dialog_message)
        val positiveButtonText = resources.getString(R.string.dialog_continue_text)

        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveButtonText) { dialog, _ ->
                dialog.cancel()
                permissions.forEach { (permissionName, requiredOsVersion) ->
                    if (Build.VERSION.SDK_INT >= requiredOsVersion) {
                        requestPermissionLauncher.launch(permissionName)
                    }
                }
            }
            .show()
    }
}