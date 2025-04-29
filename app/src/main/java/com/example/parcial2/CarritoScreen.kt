package com.example.parcial2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.parcial2.ViewModel.TiendaViewModel

@Composable
fun CarritoScreen(navController: NavController, viewModel: TiendaViewModel) {
    val snackbarHostState = remember { SnackbarHostState() }
    var mostrarSnackbar by remember { mutableStateOf(false) }

    // Mostrar Snackbar si se activó la bandera
    LaunchedEffect(mostrarSnackbar) {
        if (mostrarSnackbar) {
            snackbarHostState.showSnackbar("Compra finalizada")
            mostrarSnackbar = false
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(padding)
        ) {
            Text("Carrito de Compras", style = MaterialTheme.typography.h6)

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(viewModel.carrito) { producto ->
                    Text("${producto.nombre} - $${producto.precio}")
                }
            }

            Text("Total a pagar: $${viewModel.totalCarrito()}", modifier = Modifier.padding(8.dp))

            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
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


