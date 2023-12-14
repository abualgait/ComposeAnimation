package com.testing.composeanimationplayground.ui.pizza


data class PizzaUiComponent(
    val name: String,
    val icon: Int,
    val id: ItemId = ItemId.PEPPER
)

enum class ItemId {
    PEPPER, ONION, MEAT, TOMATO, CHEESE
}