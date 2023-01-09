package com.zcf.testapp.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import kotlinx.coroutines.suspendCancellableCoroutine

class LocationManager {

    /**
     * This demonstrates how to get device location with LocationManager.
     * After get the location, the result can be reported by Callback, Rx observable creator,
     * or [suspendCancellableCoroutine]
     */
    @SuppressLint("MissingPermission")
    fun getLocation(context: Context) {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val criteria = Criteria().apply {
            isAltitudeRequired = false
            isBearingRequired = false
            isSpeedRequired = false
        }

        val providers = locationManager.getProviders(true)
        println("zcf locationProviders: $providers")

        var lastKnownLocation: Location? = null
        for (provider in providers) {
            val location = locationManager.getLastKnownLocation(provider)
            if (location != null) {
                lastKnownLocation = location
                break
            }
        }
        println("zcf lastKnownLocaiton: $lastKnownLocation")

        if (lastKnownLocation != null) {
            println("zcf requestLocationUpdates")
            val locationListener = object : LocationListener {
                override fun onLocationChanged(location: android.location.Location) {
                    println("zcf locationUpdates: $location")
                    locationManager.removeUpdates(this)
//                emitter.onSuccess(Location(location.latitude, location.longitude).toOptional())
                }
            }
            for (provider in providers) {
                locationManager.requestLocationUpdates(provider, 1000, 0f, locationListener)
            }
        }
    }
}