package com.example.saham

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.example.saham.databinding.ActivityMainBinding
import com.example.saham.interfaces.AddressListener
import com.example.saham.ui.ModalBottomSheet
import com.example.saham.viewmodels.MainViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity(), AddressListener,
    NavController.OnDestinationChangedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val viewModel: MainViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener(this)


        binding.handler = this
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        checkLocationPermission()

        viewModel.currentLatLng.observe(this) {
            Log.e("ViewModel", "New Value")
            getLocationAddress(it)
        }
    }


    private fun checkLocationPermission() {
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions[ACCESS_FINE_LOCATION] ?: false -> {
                    getCurrentLocation()
                }
                permissions[ACCESS_COARSE_LOCATION] ?: false -> {
                    getCurrentLocation()
                }
                else -> {
                    // No location access granted.
                }
            }
        }

        locationPermissionRequest.launch(
            arrayOf(
                ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION
            )
        )
    }


    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    Log.e("Location", "Lat -> ${location.latitude} - Lng -> ${location.longitude}")
                    viewModel.setCurrentLatLng(LatLng(location.latitude, location.longitude))
                } else {
                    Log.e("Location", "null")
                }
            }
    }

    private fun getLocationAddress(latLng: LatLng) {
        try {
            val geocoder = Geocoder(this, Locale.getDefault())
            val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
            if (addresses!!.size > 0) {
                val address = addresses[0]
                if (address.getAddressLine(0) != null) {
                    Log.e("Address", addresses[0].getAddressLine(0))
                    binding.address = address.getAddressLine(0)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onAddressClick() {
        val modalBottomSheet = ModalBottomSheet()
        modalBottomSheet.show(supportFragmentManager, ModalBottomSheet.TAG)
    }

    override fun onDestinationChanged(
        controller: NavController, destination: NavDestination, arguments: Bundle?
    ) {
        when (destination.id) {
            R.id.splashFragment -> {
                hideToolbar()
                hideBottomNavigation()
            }
            else -> {
                showToolbar()
                showBottomNavigation()
            }
        }
    }

    private fun hideToolbar() {
        if (binding.toolbar.visibility == View.VISIBLE) {
            binding.toolbar.visibility = View.GONE
        }
    }

    private fun showToolbar() {
        if (binding.toolbar.visibility == View.GONE) {
            binding.toolbar.visibility = View.VISIBLE
        }
    }

    private fun showBottomNavigation() {
        if (binding.bottomNavigation.visibility == View.GONE) {
            binding.bottomNavigation.visibility = View.VISIBLE
        }
    }

    private fun hideBottomNavigation() {
        if (binding.bottomNavigation.visibility == View.VISIBLE) {
            binding.bottomNavigation.visibility = View.GONE
        }
    }
}