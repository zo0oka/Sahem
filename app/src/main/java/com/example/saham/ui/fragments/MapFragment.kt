package com.example.saham.ui.fragments

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.saham.R
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.saham.databinding.FragmentMapBinding
import com.example.saham.interfaces.MapHandler
import com.example.saham.viewmodels.MainViewModel
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import java.util.*

class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnCameraIdleListener, MapHandler {
    private lateinit var mMap: GoogleMap
    private lateinit var binding: FragmentMapBinding
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var selectedLocation: LatLng


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapBinding.inflate(layoutInflater, container, false)
        binding.handler = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_view) as SupportMapFragment
        mapFragment.getMapAsync(this)
        getCurrentLocation()
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnCameraIdleListener(this)
        mMap.isMyLocationEnabled = true
        mMap.uiSettings.setAllGesturesEnabled(true)
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    Log.e("Location", "Lat -> ${location.latitude} - Lng -> ${location.longitude}")
                    val latLng = LatLng(location.latitude, location.longitude)
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
                    viewModel.setCurrentLatLng(latLng)
                } else {
                    Log.e("Location", "null")
                }
            }
    }

    override fun onCameraIdle() {
        Log.e(
            "Selected Location",
            "Lat -> ${mMap.cameraPosition.target.latitude} - Lng -> ${mMap.cameraPosition.target.latitude}"
        )
        selectedLocation =
            LatLng(mMap.cameraPosition.target.latitude, mMap.cameraPosition.target.latitude)
    }

    override fun onSelect() {
        viewModel.setCurrentLatLng(
            LatLng(
                mMap.cameraPosition.target.latitude, mMap.cameraPosition.target.longitude
            )
        )
        findNavController().popBackStack()
    }
}