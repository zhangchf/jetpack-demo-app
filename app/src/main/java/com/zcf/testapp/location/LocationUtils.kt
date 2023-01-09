package com.zcf.testapp.location

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.google.android.gms.tasks.CancellationTokenSource
import com.zcf.testapp.utils.log
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine

/**
 * This demonstrates how to get user location with suspendCancellableCoroutine and Google FusedLocationProviderClient
 */
@OptIn(ExperimentalCoroutinesApi::class)
@SuppressLint("MissingPermission")
suspend fun FusedLocationProviderClient.currentLocation() = suspendCancellableCoroutine<Location?> {
    val cancellationTokenSource = CancellationTokenSource()
    val currentLocationTask = getCurrentLocation(
        LocationRequest.PRIORITY_HIGH_ACCURACY, cancellationTokenSource.token
    )
    log(tag, "request current location")
    currentLocationTask.addOnCompleteListener { task ->
        if (!it.isCompleted) {
            it.resume(task.result, null)
        }
    }

    lastLocation.addOnCompleteListener { last ->
        log(tag, "lastLocation complete ${last.result == null}")
        if (last.isSuccessful && last.isComplete && last.result != null) {
            log(tag, "last ${last.result.latitude} - ${last.result.longitude}")
            it.resume(last.result, null)
        }
    }

    it.invokeOnCancellation {
        cancellationTokenSource.cancel()
    }
}

fun isLocationPermissionGranted(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}

private const val tag = "FusedLocationProviderClient"