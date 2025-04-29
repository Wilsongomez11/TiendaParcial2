package com.example.parcial2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.parcial2.ViewModel.TiendaViewModel
import com.example.parcial2.ui.theme.Parcial2Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Parcial2Theme {
                val viewModel: TiendaViewModel = viewModel()
                NavGraph(viewModel = viewModel)
            }
        }
    }
}
