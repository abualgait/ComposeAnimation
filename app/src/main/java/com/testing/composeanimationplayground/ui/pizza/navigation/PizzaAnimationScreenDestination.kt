package com.testing.composeanimationplayground.ui.pizza.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.testing.composeanimationplayground.ui.pizza.PizzaAnimationScreen
import com.weather.app.navigation.AppNavigationDestination

object PizzaAnimationScreenDestination : AppNavigationDestination {
    override val route = "pizza_animation_screen_route"
    override val destination = "pizza_animation_screen_destination"
}


fun NavGraphBuilder.pizzaAnimationScreen() {
    composable(route = PizzaAnimationScreenDestination.route) {
        PizzaAnimationScreen()
    }
}
