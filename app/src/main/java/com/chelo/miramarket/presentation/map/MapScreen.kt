package com.chelo.miramarket.presentation.map

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.chelo.miramarket.R
import com.chelo.miramarket.ui.theme.Background
import com.chelo.miramarket.ui.theme.BackgroundBlack
import com.chelo.miramarket.ui.theme.SplashBackground
import org.maplibre.android.camera.CameraUpdateFactory
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.MapLibreMap
import org.maplibre.android.maps.MapView
import org.maplibre.android.plugins.annotation.SymbolManager
import org.maplibre.android.plugins.annotation.SymbolOptions

@Composable
fun MapScreen(
    modifier: Modifier = Modifier,
    viewContext: Context,
    viewmodel: MapViewModel = hiltViewModel(),
    goBack: () -> Unit,
    navigate: () -> Unit,
) {
    BackHandler {
        goBack()
    }
    val localContext = LocalContext.current

    var hasPermission by remember { mutableStateOf(false) }
    var userLocation by remember { mutableStateOf<LatLng?>(null) }

    var mapLibreMap by remember { mutableStateOf<MapLibreMap?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    val permissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {
            hasPermission = it
        }

    var locationRequest by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val granted =
            ContextCompat.checkSelfPermission(
                localContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

        if (granted) {
            hasPermission = true
        } else {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    LaunchedEffect(hasPermission) {
        if (hasPermission && !locationRequest) {
            locationRequest = true
            viewmodel.loadUserLocation()
            userLocation = viewmodel.userLocation.value
            isLoading = false
        }
    }

    val defaultLocation = LatLng(-38.26667, -57.85)
    val mapCenter = userLocation ?: defaultLocation

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BackgroundBlack)
                    .padding(horizontal = 16.dp)
                    .statusBarsPadding()
                    .navigationBarsPadding(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painterResource(R.drawable.ic_arrow_back),
                    contentDescription = "Back",
                    tint = Background,
                    modifier = Modifier.clickable {goBack()}
                )
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text(
                        "Mapa de comercios",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Background
                    )
                    Text(
                        "Explora negocios cercanos",
                        fontWeight = FontWeight.Light,
                        fontSize = 16.sp,
                        color = Color.LightGray
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navigate()
                },
                shape = CircleShape,
                containerColor = SplashBackground, modifier = Modifier
                    .padding(24.dp)
                    .size(64.dp)
            ) {
                Icon(
                    painterResource(R.drawable.ic_add),
                    contentDescription = "Agregar comercio",
                    tint = Color.White
                )
            }
        }
    ) { innerPadding ->
        if (!isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AndroidView(
                    factory = { context ->
                        MapView(context).apply {
                            onCreate(null)
                            getMapAsync { map ->
                                mapLibreMap = map
                                map.setStyle("https://api.maptiler.com/maps/dataviz/style.json?key=30CNXncovO6BSPiwby70") { style ->
                                    val icon = BitmapFactory.decodeResource(
                                        context.resources,
                                        R.drawable.png_ubi
                                    )

                                    map.animateCamera(
                                        CameraUpdateFactory.newLatLngZoom(
                                            mapCenter,
                                            20.0
                                        )
                                    )
                                    style.addImage("user-icon", icon)
                                    val symbolManager = SymbolManager(this, map, style)
                                    val marker = symbolManager.create(
                                        SymbolOptions()
                                            .withLatLng(mapCenter)
                                            .withIconImage("user-icon")
                                            .withIconSize(1f)
                                            .withTextSize(12f)
                                            .withTextField("Your location ")
                                            .withTextOffset(arrayOf(0f, 2f))
                                    )

                                }
                                map.uiSettings.apply {
                                    isZoomGesturesEnabled = true
                                    isScrollGesturesEnabled = true
                                    isRotateGesturesEnabled = true
                                    isTiltGesturesEnabled = true

                                }


                            }
                        }
                    },
                    update = { mapView ->
                        mapLibreMap?.let { map ->
                            userLocation?.let { location ->
                                val bitmap = BitmapFactory.decodeResource(
                                    mapView.resources,
                                    R.drawable.ic_location
                                )

                                map.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 20.0))

                            }

                        }
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding()

                )
            }

        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator()
                    Text(text = "Loading map...")
                }

            }
        }
    }


}
