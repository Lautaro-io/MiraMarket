package com.chelo.miramarket.presentation.splashscreen

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chelo.miramarket.R
import com.chelo.miramarket.ui.theme.SplashBackground
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navigate: () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = -10f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 400, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    LaunchedEffect(Unit)
    {
        delay(2000L)
        navigate()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SplashBackground),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.iconapp),
            contentDescription = "Icon App",
            tint = Color.White,
            modifier = Modifier
                .size(177.dp)
                .graphicsLayer {
                    rotationZ = rotation
                }
        )
        Spacer(Modifier.height(32.dp))
        Text("MiraMarket", fontWeight = FontWeight.Bold, fontSize = 32.sp, color = Color.White)
        Spacer(Modifier.height(16.dp))

        Text("Descubri comercios cerca tuyo!", fontSize = 16.sp, color = Color.LightGray)

    }
}