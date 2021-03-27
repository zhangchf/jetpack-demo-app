package com.zcf.testapp.permission

import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class PermissionManager(
    val fragment: Fragment,
    val permission: String,
    val grantedAction: () -> Unit,
    val showRationalUI: () -> Unit,
    val notGrantedAction: () -> Unit
) {

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    fun registerPermissionLauncher() {
        requestPermissionLauncher = fragment.registerForActivityResult(
            RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                grantedAction.invoke()
            } else {
                notGrantedAction.invoke()
            }
        }
    }

    fun checkAndRequestPermission() {
        when {
            ContextCompat.checkSelfPermission(fragment.requireContext(), permission) == PackageManager.PERMISSION_GRANTED -> {
                grantedAction.invoke()
            }
            fragment.shouldShowRequestPermissionRationale(permission) -> {
                showRationalUI.invoke()
            }
            else -> {
                requestPermission()
            }
        }
    }

    fun requestPermission() {
        requestPermissionLauncher.launch(permission)
    }
}