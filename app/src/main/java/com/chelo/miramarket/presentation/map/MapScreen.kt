package com.chelo.miramarket.presentation.map

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chelo.miramarket.R

@Composable
fun MapScreen(navigate: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = { navigate() }, modifier = Modifier.padding(32.dp).size(64.dp) , containerColor = Color.Black) {
                Icon(
                    painterResource(R.drawable.ic_add),
                    contentDescription = "Icon add",
                    tint = Color.White

                )
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Map screen", modifier = Modifier.padding(innerPadding))

        }

    }
}