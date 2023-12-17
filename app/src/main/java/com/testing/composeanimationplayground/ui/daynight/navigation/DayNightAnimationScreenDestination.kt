package com.testing.composeanimationplayground.ui.daynight.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.testing.composeanimationplayground.ui.daynight.DayNightAnimationScreen
import com.testing.composeanimationplayground.ui.pizza.PizzaAnimationScreen
import com.weather.app.navigation.AppNavigationDestination

object DayNightAnimationScreenDestination : AppNavigationDestination {
    override val route = "daynight_animation_screen_route"
    override val destination = "daynight_animation_screen_destination"
}


fun NavGraphBuilder.dayNightAnimationScreen() {
    composable(route = DayNightAnimationScreenDestination.route) {
        DayNightAnimationScreen()
    }
}
