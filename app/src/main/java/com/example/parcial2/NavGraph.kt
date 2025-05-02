package com.example.parcial2

    import androidx.compose.runtime.Composable
    import androidx.navigation.NavType
    import androidx.navigation.compose.*
    import androidx.navigation.navArgument
    import com.example.parcial2.ViewModel.TiendaViewModel


@Composable
fun NavGraph(viewModel: TiendaViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "catalogo") {
        composable("catalogo") {
            CatalogoScreen(navController, viewModel)
        }
        composable("registro") {
            RegistroScreen(navController, viewModel)
        }
        composable("detalle/{id}", arguments = listOf(navArgument("id") {
            type = NavType.IntType
        })) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            DetalleScreen(navController, viewModel, id)
        }
        composable("carrito") {
            CarritoScreen(navController, viewModel)
        }
    }
}