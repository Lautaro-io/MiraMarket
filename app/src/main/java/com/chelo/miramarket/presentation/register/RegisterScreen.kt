package com.chelo.miramarket.presentation.register

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.chelo.miramarket.R
import com.chelo.miramarket.ui.theme.Background
import com.chelo.miramarket.ui.theme.BackgroundBlack
import com.chelo.miramarket.ui.theme.FieldBg

@Preview(showBackground = true)
@Composable
fun RegisterScreen(navigate: () -> Unit = {}, onBack:()->Unit = { },registerViewModel: RegisterViewModel = hiltViewModel()) {
    val state = registerViewModel.state.collectAsState().value

    BackHandler {
        onBack()
    }

    Scaffold(
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
                    modifier = Modifier.clickable{onBack()}
                )
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text(
                        "Agregar comercio",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Background
                    )
                    Text(
                        "Registra tu negocio",
                        fontWeight = FontWeight.Light,
                        fontSize = 16.sp,
                        color = Color.LightGray
                    )
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(Modifier.padding(innerPadding)) {
            item {
                FormStoreInfo(state, registerViewModel)
            }
            item {
                CardUbication(state, registerViewModel)
            }
            item {
                FormOwnerInfo(state, registerViewModel)
            }
            item {
                StoreTime(state, registerViewModel)
            }
            item {
                Button(
                    onClick = { navigate() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BackgroundBlack,
                        contentColor = Background
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) { Text("Registrar comercio") }
            }
        }
    }
}

@Composable
fun FormStoreInfo(state: RegisterState, registerViewModel: RegisterViewModel) {
    Card(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Background,
            contentColor = BackgroundBlack,
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                Icon(
                    painterResource(R.drawable.ic_store),
                    contentDescription = "Informacion de comercio",
                    tint = BackgroundBlack
                )
                Text("Informacion del negocio", modifier = Modifier.padding(start = 8.dp))
            }
            FieldStore(
                labelText = "Nombre del comercio *",
                value = state.storeName,
                onValueChange = { registerViewModel.onFieldChange(FieldType.STORE_NAME, it) },
                placeHolderText = "Ej : Panaderia el Sol..."
            )
            FieldStore(
                labelText = "Categoria *",
                value = state.category,
                onValueChange = { registerViewModel.onFieldChange(FieldType.CATEGORY, it) },
                placeHolderText = "Seleccione una categoria"
            )
            FieldStore(
                labelText = "Descripcion",
                value = state.description,
                onValueChange = { registerViewModel.onFieldChange(FieldType.DESCRIPTION, it) },
                placeHolderText = "Describe tu negocio..."
            )
        }
    }
}

@Composable
fun CardUbication(state: RegisterState, registerViewModel: RegisterViewModel) {
    Card(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Background,
            contentColor = BackgroundBlack,
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                Icon(
                    painterResource(R.drawable.ic_location),
                    contentDescription = "Ubicacion",
                    tint = BackgroundBlack
                )
                Text("Ubicacion", modifier = Modifier.padding(start = 8.dp))
            }
            FieldStore(
                labelText = "Direccion **",
                value = state.address,
                onValueChange = { registerViewModel.onFieldChange(FieldType.ADDRESS, it) },
                placeHolderText = "Ej : Avenida Siempre Viva 123"
            )
        }
    }
}

@Composable
fun FormOwnerInfo(state: RegisterState, registerViewModel: RegisterViewModel) {
    Card(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Background,
            contentColor = BackgroundBlack,
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                Icon(
                    painterResource(R.drawable.ic_person),
                    contentDescription = "Datos del titular",
                    tint = BackgroundBlack
                )
                Text("Datos del titular", modifier = Modifier.padding(start = 8.dp))
            }
            FieldStore(
                labelText = "Nombre completo **",
                value = state.ownerName,
                onValueChange = { registerViewModel.onFieldChange(FieldType.OWNER_NAME, it) },
                placeHolderText = "Ej : Juan Perez..."
            )
            FieldStore(
                labelText = "Telefono **",
                value = state.phoneNumber,
                onValueChange = { registerViewModel.onFieldChange(FieldType.PHONE_NUMBER, it) },
                placeHolderText = "Ej : 2291-121212"
            )
            FieldStore(
                labelText = "Email **",
                value = state.email,
                onValueChange = { registerViewModel.onFieldChange(FieldType.EMAIL, it) },
                placeHolderText = "Ej : juanperez@gmail.com"
            )
        }
    }
}

@Composable
fun StoreTime(state: RegisterState, registerViewModel: RegisterViewModel) {
    Card(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Background,
            contentColor = BackgroundBlack,
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                Icon(
                    painterResource(R.drawable.ic_open_soon),
                    contentDescription = "Horarios",
                    tint = BackgroundBlack,
                    modifier = Modifier.size(24.dp)
                )
                Text("Horarios", modifier = Modifier.padding(start = 8.dp))
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FieldStore(
                    labelText = "Apertura **",
                    value = state.openingTime,
                    onValueChange = { registerViewModel.onFieldChange(FieldType.OPENING_TIME, it) },
                    placeHolderText = "Ej : -- : --",
                    modifier = Modifier.weight(1f)
                )
                FieldStore(
                    labelText = "Cierre **",
                    value = state.closingTime,
                    onValueChange = { registerViewModel.onFieldChange(FieldType.CLOSING_TIME, it) },
                    placeHolderText = "Ej : -- : --",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}


@Composable
fun FieldStore(
    modifier: Modifier = Modifier,
    labelText: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeHolderText: String,
    isSingleLine: Boolean = true,
    minLines: Int? = null,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = labelText,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = BackgroundBlack,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(text = placeHolderText, color = Color.Gray) },
            shape = RoundedCornerShape(8.dp),
            singleLine = isSingleLine,
            minLines = minLines ?: 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = BackgroundBlack,
                unfocusedBorderColor = Color.LightGray,
                focusedTextColor = BackgroundBlack,
                unfocusedTextColor = BackgroundBlack,
                focusedContainerColor = Background,
                unfocusedContainerColor = FieldBg.copy(alpha = 0.3f),


            )
        )
    }
}