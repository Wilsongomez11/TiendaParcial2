package com.example.parcial2

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.parcial2.ViewModel.TiendaViewModel
import kotlin.text.isBlank
import kotlin.text.isNotBlank
import kotlin.text.toDoubleOrNull

@Composable
fun RegistroScreen(navController: NavController, viewModel: TiendaViewModel) {
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var imagenUrl by remember { mutableStateOf("") }
    var errorMensaje by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Agregar Producto", style = MaterialTheme.typography.h6)

        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
        OutlinedTextField(value = precio, onValueChange = { precio = it }, label = { Text("Precio") })
        OutlinedTextField(value = descripcion, onValueChange = { descripcion = it }, label = { Text("Descripción") })
        OutlinedTextField(value = imagenUrl, onValueChange = { imagenUrl = it }, label = { Text("URL de imagen") })

        if (errorMensaje.isNotBlank()) {
            Text(errorMensaje, color = MaterialTheme.colors.error)
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {
                if (nombre.isBlank() || precio.isBlank() || descripcion.isBlank() || imagenUrl.isBlank()) {
                    errorMensaje = "Todos los campos son obligatorios"
                    return@Button
                }

                val precioDouble = precio.toDoubleOrNull()
                if (precioDouble == null) {
                    errorMensaje = "Precio inválido"
                    return@Button
                }

                viewModel.agregarProducto(nombre, precioDouble, descripcion, imagenUrl)
                navController.popBackStack()
            }) {
                Text("Guardar")
            }

            Button(onClick = { navController.popBackStack() }) {
                Text("Cancelar")
            }
        }
    }
}
