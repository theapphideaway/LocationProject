package com.example.myapplication

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    var TAG: String = "MainActivity"
    var FINE_LOCATION_REQUEST: Int = 888

    lateinit var locationRequest: LocationRequest
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (checkPermissions()) {
            initLocationUpdate()
        }

    }

    @SuppressLint("MissingPermission")
    //Start Location update as define intervals
    fun initLocationUpdate(){

        // Check API revision for New Location Update
        //https://developers.google.com/android/guides/releases#june_2017_-_version_110

        //init location request to start retrieving location update
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        //Create LocationSettingRequest object using locationRequest
        val locationSettingBuilder: LocationSettingsRequest.Builder = LocationSettingsRequest.Builder()
        locationSettingBuilder.addLocationRequest(locationRequest)
        val locationSetting: LocationSettingsRequest = locationSettingBuilder.build()

        //Need to check whether location settings are satisfied
        val settingsClient: SettingsClient = LocationServices.getSettingsClient(this)
        settingsClient.checkLocationSettings(locationSetting)
        //More info :  // https://developers.google.com/android/reference/com/google/android/gms/location/SettingsClient


        // new Google API SDK v11 uses getFusedLocationProviderClient(this)
        val fusedLocationProviderClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                //super.onLocationResult(p0)
                if (locationResult != null) {
                    onLocationChanged(locationResult.lastLocation)
                    locationResult.lastLocation.latitude.toString()
                    locationResult.lastLocation.longitude.toString()
                }
            }

            override fun onLocationAvailability(p0: LocationAvailability?) {
                super.onLocationAvailability(p0)
            }
        },
            Looper.myLooper())


    }

    fun onLocationChanged(location: Location): Coordinates {
        // New location has now been determined
        val coordinates = Coordinates()
        coordinates.latitude = java.lang.Double.toString(location.latitude)
        coordinates.longitude = java.lang.Double.toString(location.longitude)

        latitude_textview.text = coordinates.latitude
        longitude_textview.text = coordinates.longitude

        return coordinates
    }

    private fun checkPermissions(): Boolean {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true
        } else {
            requestPermissions()
            return false
        }
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            FINE_LOCATION_REQUEST)
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == FINE_LOCATION_REQUEST) {
            // Received permission result for Location permission.
            Log.i(TAG, "Received response for Location permission request.")

            // Check if the only required permission has been granted
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Camera permission has been granted, preview can be displayed
                Log.i(TAG, "Location permission has now been granted. Now call initLocationUpdate")
                initLocationUpdate()
            } else {


            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
}

class Coordinates{
    var latitude: String? = null
    var longitude: String? = null
}