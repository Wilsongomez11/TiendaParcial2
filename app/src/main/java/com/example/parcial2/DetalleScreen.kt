package com.example.parcial2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.request.ImageRequest
import com.example.parcial2.ViewModel.TiendaViewModel

@Composable
fun DetalleScreen(navController: NavController, viewModel: TiendaViewModel, productoId: Int?) {
    if (productoId == null) {
        Text("ID no válido", modifier = Modifier.padding(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
        return
    }

    val producto = viewModel.obtenerProductoPorId(productoId)
    if (producto == null) {
        Text("Producto no encontrado", modifier = Modifier.padding(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Detalle del Producto") })
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {
                    viewModel.agregarAlCarrito(producto)
                    navController.popBackStack()
                }) {
                    Text("Agregar al Carrito")
                }
                Button(onClick = { navController.popBackStack() }) {
                    Text("Volver")
                }
            }
        }
    ) { padding ->
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(producto.imagenUrl)
                        .crossfade(true)
                        .build()
                )

                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .padding(8.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text(producto.nombre, style = MaterialTheme.typography.h5)
                Text("Precio: $${producto.precio}", style = MaterialTheme.typography.subtitle1)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Descripción: ${producto.descripcion}", style = MaterialTheme.typography.body2)
            }
        }
    }
}
