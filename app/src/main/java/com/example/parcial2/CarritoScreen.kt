package com.example.parcial2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.parcial2.ViewModel.TiendaViewModel
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun CarritoScreen(navController: NavController, viewModel: TiendaViewModel) {
    val snackbarHostState = remember { SnackbarHostState() }
    var mostrarSnackbar by remember { mutableStateOf(false) }

    LaunchedEffect(mostrarSnackbar) {
        if (mostrarSnackbar) {
            snackbarHostState.showSnackbar("Compra finalizada")
            mostrarSnackbar = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Carrito de Compras") })
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(padding)) {



                    LazyColumn(modifier = Modifier.weight(1f)) {
                        items(viewModel.carrito) { producto ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                elevation = 8.dp
                            ) {
                                Row(modifier = Modifier.padding(12.dp)) {
                                    val painter = rememberAsyncImagePainter(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(producto.imagenUrl)
                                            .crossfade(true)
                                            .build()
                                    )

                                    Image(
                                        painter = painter,
                                        contentDescription = null,
                                        modifier = Modifier.size(80.dp)
                                    )

                                    Spacer(modifier = Modifier.width(12.dp))

                                    Column {
                                        Text(producto.nombre, style = MaterialTheme.typography.h6)
                                        Text("Precio: $${producto.precio}", style = MaterialTheme.typography.body2)
                                    }
                                }
                            }
                        }
                    }


            Text(
                text = "Total a pagar: $${viewModel.totalCarrito()}",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(vertical = 8.dp)
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = {
                    viewModel.limpiarCarrito()
                    mostrarSnackbar = true
                }) {
                    Text("Finalizar Compra")
                }
                Button(onClick = { navController.popBackStack() }) {
                    Text("Volver")
                }
            }
        }
    }
}


