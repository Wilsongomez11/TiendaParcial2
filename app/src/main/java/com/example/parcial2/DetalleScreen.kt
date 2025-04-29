package com.example.parcial2

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import kotlin.let
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.parcial2.ViewModel.TiendaViewModel

@Composable
fun DetalleScreen(navController: NavController, viewModel: TiendaViewModel, productoId: Int?) {
    val producto = productoId?.let { viewModel.obtenerProductoPorId(it) }

    if (producto == null) {
        Text("Producto no encontrado", modifier = Modifier.padding(16.dp))
        Button(onClick = { navController.popBackStack() }, modifier = Modifier.padding(16.dp)) {
            Text("Volver")
        }
        return
    }

    Column(modifier = Modifier.padding(16.dp)) {
        val painter = rememberAsyncImagePainter(
            model = producto.imagenUrl,
            error = painterResource(R.drawable.ic_delete),
            placeholder = painterResource(R.drawable.progress_indeterminate_horizontal)
        )

        Image(painter = painter, contentDescription = null, modifier = Modifier.size(200.dp))

        Text(producto.nombre, style = MaterialTheme.typography.h6)
        Text("Precio: $${producto.precio}")
        Text("Descripción: ${producto.descripcion}")

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
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
}
