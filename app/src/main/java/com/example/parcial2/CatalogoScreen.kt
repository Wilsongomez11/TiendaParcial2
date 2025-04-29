package com.example.parcial2

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.parcial2.ViewModel.TiendaViewModel

@Composable
fun CatalogoScreen(navController: NavController, viewModel: TiendaViewModel) {
    Column {
        Text(
            "Catálogo de Productos",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(8.dp)
        )

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(viewModel.productos) { producto ->
                ProductoItem(producto) {
                    navController.navigate("detalle/${producto.id}")
                }
            }
        }

        Text("Total carrito: $${viewModel.totalCarrito()}", modifier = Modifier.padding(8.dp))

        Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { navController.navigate("registro") }) {
                Text("Agregar Producto")
            }
            Button(onClick = { navController.navigate("carrito") }) {
                Text("Ir al Carrito")
            }
        }
    }
}

@Composable
fun ProductoItem(producto: Producto, onClick: () -> Unit) {
    Row(
        modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .padding(8.dp)) {

        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(producto.imagenUrl)
                .crossfade(true)
                .size(Size.ORIGINAL)
                .build()
        )

        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(producto.nombre, style = MaterialTheme.typography.subtitle1)
            Text("Precio: $${producto.precio}")
        }
    }
}
