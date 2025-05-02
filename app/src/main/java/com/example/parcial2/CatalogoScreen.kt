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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.parcial2.ViewModel.TiendaViewModel

@Composable
fun CatalogoScreen(navController: NavController, viewModel: TiendaViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Catálogo de Productos") })
        },
        content = { padding ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(padding)) {

                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(viewModel.productos) { producto ->
                        ProductoCard(producto = producto) {
                            navController.navigate("detalle/${producto.id}")
                        }
                    }
                }

                Text(
                    text = "Total carrito: $${viewModel.totalCarrito()}",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = { navController.navigate("registro") }) {
                        Text("Agregar Producto")
                    }
                    Button(onClick = { navController.navigate("carrito") }) {
                        Text("Ir al Carrito")
                    }
                }
            }
        }
    )
}

@Composable
fun ProductoCard(producto: Producto, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = 8.dp
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            val painter = rememberAsyncImagePainter(model = producto.imagenUrl)

            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(producto.nombre, style = MaterialTheme.typography.h6)
                Text("Precio: $${producto.precio}", style = MaterialTheme.typography.body2)
            }
        }
    }
}