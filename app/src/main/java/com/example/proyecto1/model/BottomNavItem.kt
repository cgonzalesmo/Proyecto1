package com.example.proyecto1.model

import com.example.proyecto1.R
import com.example.proyecto1.screens.destinations.Destination
import com.example.proyecto1.screens.destinations.HomeScreenDestination
import com.example.proyecto1.screens.destinations.SearchScreenDestination
import com.example.proyecto1.screens.destinations.SettingScreenDestination

sealed class BottomNavItem(var title: String, var icon: Int, var destination: Destination) {
    object Home : BottomNavItem(
        title = "Inicio",
        icon = R.drawable.ic_home,
        destination = HomeScreenDestination
    )
    object Search: BottomNavItem(
        title = "Buscar",
        icon = R.drawable.ic_search,
        destination = SearchScreenDestination
    )
    object Settings: BottomNavItem(
        title = "Configuracion",
        icon = R.drawable.ic_settings,
        destination = SettingScreenDestination
    )
}