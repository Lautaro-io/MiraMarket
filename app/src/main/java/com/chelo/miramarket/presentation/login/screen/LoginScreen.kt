package com.chelo.miramarket.presentation.login.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.chelo.miramarket.R
import com.chelo.miramarket.presentation.login.viewmodel.LoginViewModel

@SuppressLint("ContextCastToActivity")
@Composable
fun LoginScreen(loginViewModel: LoginViewModel = hiltViewModel(), navigate: () -> Unit) {
//
//    val context = LocalContext.current as Activity
//    val state by loginViewModel.state.collectAsState()
//
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Button(onClick = {
//            loginViewModel.signInWithGoogle(context)
//            if (state.currentUser != null) {
//                navigate()
//            }
//        }) { Text("Ingresar con Google") }
//    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 32.dp)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Spacer(modifier = Modifier.height(80.dp))

            Image(
                painter = painterResource(R.drawable.iconapp_2),
                contentDescription = "Icon App",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text("MiraMarket", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "¡Descubrí comercios cerca tuyo!",
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(90.dp))
            Text(
                text = "¡Bienvenido!",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Inicia sesión para explorar negocios locales y registrar tu comercio",
                fontSize = 15.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(50.dp))

            OutlinedButton(
                onClick = { navigate() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                border = BorderStroke(1.dp, Color.LightGray)
            )
            {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                )

                {
                    Image(
                        painter = painterResource(id = R.drawable.google_color),
                        contentDescription = "Google Logo",
                        modifier = Modifier
                            .size(24.dp)
                            .offset(y = (-1).dp)
                            .offset(x = (-35).dp)
                    )

                    Text(
                        "Continuar con Google",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }

            }

            Spacer(modifier = Modifier.height(30.dp))

            val annotatedText = buildAnnotatedString {
                append("Al continuar, aceptas nuestros ")

                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline,
                        color = Color.Black
                    )
                ) {
                    append("Términos de Servicio y Política de Privacidad")

                }
            }
            Text(
                text = annotatedText,
                style = MaterialTheme.typography.bodySmall.copy(color = Color.Black),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            )
        }
    }
}
