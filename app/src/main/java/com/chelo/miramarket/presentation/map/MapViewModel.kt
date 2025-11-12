package com.chelo.miramarket.presentation.map

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeoutOrNull
import org.maplibre.android.geometry.LatLng
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
) : ViewModel() {

    private val _state = MutableStateFlow(MapState())
    private val _userLocation = MutableStateFlow<LatLng?>(null)
    val userLocation = _userLocation.asStateFlow()
    val state = _state.asStateFlow()






    fun loadUserLocation() {
        viewModelScope.launch {
            _userLocation.value = getUserLocation(context)
            _state.value = _state.value.copy(isLoading = false)

        }
    }

    @SuppressLint("MissingPermission")
    private suspend fun getUserLocation(context: Context): LatLng {
        val currentUser = LocationServices.getFusedLocationProviderClient(context)

        val defaultLocation = LatLng(-38.26667, -57.85)

        return try {
            coroutineScope {

                val lastLocation = async { currentUser.lastLocation.await() }
                val currentLocation = async {
                    withTimeoutOrNull(3000L) {
                        currentUser.getCurrentLocation(
                            Priority.PRIORITY_BALANCED_POWER_ACCURACY, null
                        ).await()
                    }
                }

                val current = currentLocation.await()
                val last = lastLocation.await()
                Log.i("CHELO", "current  ? $current")
                Log.i("CHELO", "last ?  $last")
                when {
                    current != null -> {
                        LatLng(
                            current.latitude,
                            current.longitude
                        )
                    }
                    last != null -> {
                        LatLng (last.latitude, last.longitude)
                    }
                    else -> {
                        defaultLocation
                    }

                }


            }
        } catch (e: Exception) {
            e.printStackTrace();defaultLocation
        }


    }


}


data class MapState(
    val isLoading: Boolean = true,
    val error: Boolean = false,
)