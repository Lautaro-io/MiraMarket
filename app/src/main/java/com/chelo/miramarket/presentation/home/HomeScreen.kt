package com.chelo.miramarket.presentation.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.chelo.miramarket.R
import com.chelo.miramarket.domain.model.Category
import com.chelo.miramarket.domain.model.Store
import com.chelo.miramarket.domain.model.StoreOwner
import com.chelo.miramarket.domain.model.StoreState
import com.chelo.miramarket.domain.model.TimeRange
import com.chelo.miramarket.ui.theme.Background
import com.chelo.miramarket.ui.theme.OpenSoonStore
import com.chelo.miramarket.ui.theme.OpenStore
import com.chelo.miramarket.ui.theme.SplashBackground
import java.time.LocalTime

val storelist: List<Store> = listOf(
    Store(
        id = "S001",
        category = Category(name = "Cafetería"),
        name = "Nombre store",
        state = StoreState.OPEN,
        description = "El mejor café de la ciudad, recién tostado.",
        ubication = "Calle Falsa 123",
        owner = StoreOwner(
            id = "O001",
            fullName = "Ana Pérez",
            phone = "555-1234"
        ),
        schedule = listOf(
            TimeRange(
                start = LocalTime.of(8, 0),
                end = LocalTime.of(17, 0)
            )
        ),
        image = "https://picsum.photos/200" // Imagen original
    ),
    Store(
        id = "S002",
        category = Category(name = "Librería"),
        name = "Nombre store",
        state = StoreState.CLOSED,
        description = "Amplia selección de libros y novelas.",
        ubication = "Avenida Siempreviva 742",
        owner = StoreOwner(
            id = "O002",
            fullName = "Roberto Gómez",
            phone = "555-5678"
        ),
        schedule = listOf(
            TimeRange(
                start = LocalTime.of(9, 30),
                end = LocalTime.of(19, 0)
            )
        ),
        image = "https://picsum.photos/200" // Imagen original
    ),
    Store(
        id = "S003",
        category = Category(name = "Restaurante"),
        name = "Nombre store",
        state = StoreState.OPEN_SOON,
        description = "Especialidades en comida mediterránea.",
        ubication = "Plaza Central, Local 10",
        owner = StoreOwner(
            id = "O003",
            fullName = "Carla Soto",
            phone = "555-9012"
        ),
        schedule = listOf(
            TimeRange(
                start = LocalTime.of(12, 0),
                end = LocalTime.of(15, 0)
            ),
            TimeRange(
                start = LocalTime.of(19, 0),
                end = LocalTime.of(23, 30)
            )
        ),
        image = "https://picsum.photos/200" // Imagen original
    ),
    Store(
        id = "S003",
        category = Category(name = "Restaurante"),
        name = "Nombre store",
        state = StoreState.OPEN_SOON,
        description = "Especialidades en comida mediterránea.",
        ubication = "Plaza Central, Local 10",
        owner = StoreOwner(
            id = "O003",
            fullName = "Carla Soto",
            phone = "555-9012"
        ),
        schedule = listOf(
            TimeRange(
                start = LocalTime.of(12, 0),
                end = LocalTime.of(15, 0)
            ),
            TimeRange(
                start = LocalTime.of(19, 0),
                end = LocalTime.of(23, 30)
            )
        ),
        image = "https://picsum.photos/200" // Imagen original
    ),
    Store(
        id = "S003",
        category = Category(name = "Restaurante"),
        name = "Nombre store",
        state = StoreState.OPEN_SOON,
        description = "Especialidades en comida mediterránea.",
        ubication = "Plaza Central, Local 10",
        owner = StoreOwner(
            id = "O003",
            fullName = "Carla Soto",
            phone = "555-9012"
        ),
        schedule = listOf(
            TimeRange(
                start = LocalTime.of(12, 0),
                end = LocalTime.of(15, 0)
            ),
            TimeRange(
                start = LocalTime.of(19, 0),
                end = LocalTime.of(23, 30)
            )
        ),
        image = "https://picsum.photos/200" // Imagen original
    ),
    Store(
        id = "S003",
        category = Category(name = "Restaurante"),
        name = "Nombre store",
        state = StoreState.OPEN_SOON,
        description = "Especialidades en comida mediterránea.",
        ubication = "Plaza Central, Local 10",
        owner = StoreOwner(
            id = "O003",
            fullName = "Carla Soto",
            phone = "555-9012"
        ),
        schedule = listOf(
            TimeRange(
                start = LocalTime.of(12, 0),
                end = LocalTime.of(15, 0)
            ),
            TimeRange(
                start = LocalTime.of(19, 0),
                end = LocalTime.of(23, 30)
            )
        ),
        image = "https://picsum.photos/200" // Imagen original
    ),
    Store(
        id = "S003",
        category = Category(name = "Restaurante"),
        name = "Nombre store",
        state = StoreState.OPEN_SOON,
        description = "Especialidades en comida mediterránea.",
        ubication = "Plaza Central, Local 10",
        owner = StoreOwner(
            id = "O003",
            fullName = "Carla Soto",
            phone = "555-9012"
        ),
        schedule = listOf(
            TimeRange(
                start = LocalTime.of(12, 0),
                end = LocalTime.of(15, 0)
            ),
            TimeRange(
                start = LocalTime.of(19, 0),
                end = LocalTime.of(23, 30)
            )
        ),
        image = "https://picsum.photos/200" // Imagen original
    ),
    Store(
        id = "S003",
        category = Category(name = "Restaurante"),
        name = "Nombre store",
        state = StoreState.OPEN_SOON,
        description = "Especialidades en comida mediterránea.",
        ubication = "Plaza Central, Local 10",
        owner = StoreOwner(
            id = "O003",
            fullName = "Carla Soto",
            phone = "555-9012"
        ),
        schedule = listOf(
            TimeRange(
                start = LocalTime.of(12, 0),
                end = LocalTime.of(15, 0)
            ),
            TimeRange(
                start = LocalTime.of(19, 0),
                end = LocalTime.of(23, 30)
            )
        ),
        image = "https://picsum.photos/200" // Imagen original
    ),
    Store(
        id = "S003",
        category = Category(name = "Restaurante"),
        name = "Nombre store",
        state = StoreState.OPEN_SOON,
        description = "Especialidades en comida mediterránea.",
        ubication = "Plaza Central, Local 10",
        owner = StoreOwner(
            id = "O003",
            fullName = "Carla Soto",
            phone = "555-9012"
        ),
        schedule = listOf(
            TimeRange(
                start = LocalTime.of(12, 0),
                end = LocalTime.of(15, 0)
            ),
            TimeRange(
                start = LocalTime.of(19, 0),
                end = LocalTime.of(23, 30)
            )
        ),
        image = "https://picsum.photos/200" // Imagen original
    ),
    Store(
        id = "S003",
        category = Category(name = "Restaurante"),
        name = "Nombre store",
        state = StoreState.OPEN_SOON,
        description = "Especialidades en comida mediterránea.",
        ubication = "Plaza Central, Local 10",
        owner = StoreOwner(
            id = "O003",
            fullName = "Carla Soto",
            phone = "555-9012"
        ),
        schedule = listOf(
            TimeRange(
                start = LocalTime.of(12, 0),
                end = LocalTime.of(15, 0)
            ),
            TimeRange(
                start = LocalTime.of(19, 0),
                end = LocalTime.of(23, 30)
            )
        ),
        image = "https://picsum.photos/200" // Imagen original
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun HomeScreen(navigateToMap: () -> Unit = {}) {

    val favorites = remember { mutableListOf<Store>() }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
                    .statusBarsPadding()
            ) {
                Text(
                    "MiraMarket",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Icon(
                    painterResource(R.drawable.iconapp),
                    contentDescription = "IconApp",
                    Modifier
                        .size(48.dp)
                        .padding(start = 8.dp)
                )

            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToMap() },
                modifier = Modifier
                    .padding(32.dp)
                    .size(64.dp),
                shape = CircleShape,
                containerColor = SplashBackground,
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_location),
                    contentDescription = "Icon ubi",
                    tint = Color.White
                )
            }
        },
        containerColor = Background

    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {
                Text(
                    "Comercios",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp, vertical = 16.dp),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
            }
            items(count = storelist.size, itemContent = { index ->
                CardStore(
                    store = storelist[index],
                    onFavoritePressed = {
                        favorites.add(storelist[index])
                    },
                    isFavorite = favorites.contains(storelist[index])
                )
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardStore(
    store: Store = storelist[0],
    onFavoritePressed: () -> Unit = {},
    isFavorite: Boolean = false,
) {
    val timeColor = when (store.state) {
        StoreState.OPEN -> OpenStore
        StoreState.CLOSED -> Color.Red
        StoreState.OPEN_SOON -> OpenSoonStore
        StoreState.PENDING -> Color.Yellow
    }
    val iconState = when (store.state) {
        StoreState.OPEN -> R.drawable.ic_open
        StoreState.CLOSED -> R.drawable.ic_close
        StoreState.OPEN_SOON -> R.drawable.ic_open_soon
        StoreState.PENDING -> R.drawable.ic_pending
    }
    val state = when (store.state) {
        StoreState.OPEN -> "Abierto"
        StoreState.CLOSED -> "Cerrado"
        StoreState.OPEN_SOON -> "Abre a las"
        StoreState.PENDING -> "Pendiente"
    }
    Card(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {
            AsyncImage(
                model = store.image,
                contentDescription = "Imagen de la tienda",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .wrapContentHeight()
                    .align(Alignment.CenterVertically)
                    .clip(RoundedCornerShape(8.dp)),
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(2f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    store.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 4.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_location),
                        contentDescription = "Ubicación",
                        modifier = Modifier.size(16.dp),
                        tint = Color.Gray
                    );
                    Text(store.ubication, color = Color.Gray)
                }
                store.schedule.forEach {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            painterResource(iconState),
                            contentDescription = "Horario ",
                            modifier = Modifier.size(16.dp)
                            ,tint = timeColor
                        )
                        Text("$state ${it.start} a ${it.end}", color = timeColor, fontSize = 12.sp)
                    }
                }
            }
            FavoriteButton(onClick = { onFavoritePressed() }, isFavorite = isFavorite)
        }


    }

}

@Composable
fun FavoriteButton(isFavorite: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    var isFav by remember { mutableStateOf(false) }
    val bgColor = if (isFav) Color(0xFF29472A) else Color.Transparent
    IconButton(
        onClick = { isFav = !isFav },
        modifier = modifier
            .padding(start = 16.dp, top = 4.dp)
            .size(24.dp),
        colors = IconButtonDefaults.iconButtonColors(containerColor = bgColor)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_heart),
            contentDescription = "Marcar como favorito",
            tint = if (isFavorite) Color(0xFF4CAF50) else Color.LightGray,
            modifier = Modifier.size(12.dp)
        )
    }
}