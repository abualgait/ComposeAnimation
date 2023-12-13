package com.testing.composeanimationplayground.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kotlin.random.Random

val IMAGE_SIZE = 60.dp
val IMAGE_PADDING = 7.dp
val INNER_IMAGE_PADDING = 5.dp
val ROUNDED_CORNER = 10.dp


data class Position(val x: Int, val y: Int)

fun generateRandomPositions(
    numPositions: Int,
    width: Int,
    height: Int,
    startX: Int = 0,
    startY: Int = 0
): List<Position> {
    val positions = mutableListOf<Position>()
    repeat(numPositions) {
        val x = Random.nextInt(startX, width)
        val y = Random.nextInt(startY, height)
        positions.add(Position(x, y))
    }
    return positions
}

fun Modifier.roundImage(): Modifier {
    return size(IMAGE_SIZE)
        .padding(IMAGE_PADDING)
        .clip(RoundedCornerShape(ROUNDED_CORNER))
}