package com.testing.composeanimationplayground.ui.weather.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.testing.composeanimationplayground.ui.weather.WeatherAnimationScreen
import com.weather.app.navigation.AppNavigationDestination

object WeatherAnimationScreenDestination : AppNavigationDestination {
    override val route = "weather_animation_screen_route"
    override val destination = "weather_animation_screen_destination"
}


fun NavGraphBuilder.weatherAnimationScreen() {
    composable(route = WeatherAnimationScreenDestination.route) {
        WeatherAnimationScreen()
    }
}
