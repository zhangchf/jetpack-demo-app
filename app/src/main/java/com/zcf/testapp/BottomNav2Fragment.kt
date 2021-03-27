package com.zcf.testapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.zcf.testapp.databinding.FragmentBottomNav2Binding
import com.zcf.testapp.permission.PermissionManager

class BottomNav2Fragment: Fragment() {

    private lateinit var binding: FragmentBottomNav2Binding

    private val permissionManager = PermissionManager(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION,
        this::requestLocation,
        this::showLocaitonPermissionRationalUI,
        this::showNoLocationPermissionError
    )

    private lateinit var locationManager: LocationManager


    override fun onAttach(context: Context) {
        super.onAttach(context)
        permissionManager.registerPermissionLauncher()
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomNav2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textContent.setOnClickListener {
            navigateToDetailsScreen(2)
        }

        permissionManager.checkAndRequestPermission()
    }


    private val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            Log.i("zcf", "location: $location")
            locationManager.removeUpdates(this)
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestLocation() {
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            1000,
            1f,
            locationListener)
    }

    private fun showLocaitonPermissionRationalUI() {
        val snackbar = Snackbar.make(binding.root, "Rational: This app needs location.", Snackbar.LENGTH_SHORT)
        snackbar.setAction("Grant") {
            permissionManager.requestPermission()
        }
        snackbar.show()
    }

    private fun showNoLocationPermissionError() {
        Snackbar.make(binding.root, "Error: No location permission.", Snackbar.LENGTH_SHORT).show()
    }

    private fun navigateToDetailsScreen(contentId: Int) {
        val direction = BottomNav2FragmentDirections
                .actionBottomNav2FragmentToBottomNav4Fragment()
                .setContentId(contentId)
        findNavController().navigate(direction)
    }
}