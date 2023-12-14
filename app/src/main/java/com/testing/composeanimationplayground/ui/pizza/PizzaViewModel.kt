package com.testing.composeanimationplayground.ui.pizza

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.testing.composeanimationplayground.R

class PizzaViewModel : ViewModel() {

    var isCurrentlyDragging by mutableStateOf(false)
        private set

    var items by mutableStateOf(emptyList<PizzaUiComponent>())
        private set

    var addedPizzaComponents = mutableStateListOf<PizzaUiComponent>()
        private set

    init {
        items = listOf(
            PizzaUiComponent("Pepper", R.drawable.pepper, ItemId.PEPPER),
            PizzaUiComponent("Onion", R.drawable.onion, ItemId.ONION),
            PizzaUiComponent("Meat", R.drawable.meat, ItemId.MEAT),
            PizzaUiComponent("Cheese", R.drawable.cheese, ItemId.CHEESE),
            PizzaUiComponent("Onion", R.drawable.tomato, ItemId.TOMATO),

            )
    }

    fun startDragging() {
        isCurrentlyDragging = true
    }

    fun stopDragging() {
        isCurrentlyDragging = false
    }

    fun addItem(pizzaUiComponent: PizzaUiComponent) {
        addedPizzaComponents.add(pizzaUiComponent)
    }


}