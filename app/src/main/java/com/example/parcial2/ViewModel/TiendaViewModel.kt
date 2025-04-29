package com.example.parcial2.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.parcial2.Producto
import kotlin.collections.plus

class TiendaViewModel : ViewModel() {
    private var contadorId = 0
    var productos by mutableStateOf(listOf<Producto>())
        private set

    var carrito by mutableStateOf(listOf<Producto>())
        private set

    fun agregarProducto(nombre: String, precio: Double, descripcion: String, imagenUrl: String) {
        val nuevoProducto = Producto(
            id = contadorId++,
            nombre = nombre,
            precio = precio,
            descripcion = descripcion,
            imagenUrl = imagenUrl
        )
        productos = productos + nuevoProducto
    }

    fun obtenerProductoPorId(id: Int): Producto? {
        return productos.find { it.id == id }
    }

    fun agregarAlCarrito(producto: Producto) {
        carrito = carrito + producto
    }

    fun totalCarrito(): Double {
        return carrito.sumOf { it.precio }
    }

    fun limpiarCarrito() {
        carrito = listOf()
    }
}