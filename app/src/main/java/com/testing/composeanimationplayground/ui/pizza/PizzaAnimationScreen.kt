package com.testing.composeanimationplayground.ui.pizza


import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.testing.composeanimationplayground.R
import com.testing.composeanimationplayground.ui.IMAGE_PADDING
import com.testing.composeanimationplayground.ui.INNER_IMAGE_PADDING
import com.testing.composeanimationplayground.ui.Position
import com.testing.composeanimationplayground.ui.ROUNDED_CORNER
import com.testing.composeanimationplayground.ui.generateRandomPositions
import com.testing.composeanimationplayground.ui.roundImage
import com.testing.composeanimationplayground.ui.theme.Grey1
import kotlin.math.sqrt
import kotlin.random.Random


@Composable
fun PizzaAnimationScreen() {
    DragableScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        Color(0xFF03A9F4),
                        Color(0xFFFFFFFF),
                    )
                )
            )
    ) {
        val squareSizeFitInPizzaSize = (155 / sqrt(2f)).dp.value.toInt()
        val numberOfPizzaComponents = 5
        val pizzaViewModel = PizzaViewModel()

        var randomPepper by remember {
            mutableStateOf(listOf<Position>())
        }


        var randomCheese by remember {
            mutableStateOf(listOf<Position>())
        }

        var randomOnion by remember {
            mutableStateOf(listOf<Position>())
        }


        var randomMeat by remember {
            mutableStateOf(listOf<Position>())
        }

        var randomTomato by remember {
            mutableStateOf(listOf<Position>())
        }

        var positionPizza by remember { mutableStateOf(Offset.Zero) }
        var sizePizza by remember { mutableStateOf(IntSize.Zero) }
        Box(
            Modifier
                .align(Alignment.Center)
                .size(300.dp), contentAlignment = Alignment.Center
        ) {

            Image(
                painterResource(R.drawable.pizza_board),
                contentDescription = "",
                modifier = Modifier.fillMaxSize()
            )
            DropItem<PizzaUiComponent>(
                modifier = Modifier
                    .fillMaxSize()
            ) { isInBound, item ->
                if (item != null) {
                    LaunchedEffect(key1 = item) {
                        pizzaViewModel.addItem(item)
                        when (item.id) {
                            ItemId.PEPPER -> {
                                randomPepper = generateRandomPositions(
                                    numPositions = numberOfPizzaComponents,
                                    width = squareSizeFitInPizzaSize,
                                    height = squareSizeFitInPizzaSize
                                )
                            }

                            ItemId.ONION -> {
                                randomOnion = generateRandomPositions(
                                    numPositions = numberOfPizzaComponents,
                                    width = squareSizeFitInPizzaSize,
                                    height = squareSizeFitInPizzaSize
                                )
                            }

                            ItemId.MEAT -> {
                                randomMeat = generateRandomPositions(
                                    numPositions = numberOfPizzaComponents,
                                    width = squareSizeFitInPizzaSize,
                                    height = squareSizeFitInPizzaSize
                                )
                            }

                            ItemId.TOMATO -> {
                                randomTomato = generateRandomPositions(
                                    numPositions = numberOfPizzaComponents,
                                    width = squareSizeFitInPizzaSize,
                                    height = squareSizeFitInPizzaSize
                                )
                            }

                            ItemId.CHEESE -> {
                                randomCheese = generateRandomPositions(
                                    numPositions = numberOfPizzaComponents,
                                    width = squareSizeFitInPizzaSize,
                                    height = squareSizeFitInPizzaSize
                                )
                            }

                            else -> {}
                        }

                    }
                }

                Image(
                    painterResource(R.drawable.pizza),
                    contentDescription = "",
                    modifier = Modifier
                        .size(230.dp)
                        .align(Alignment.Center)
                        .scale(if (isInBound) 1.1f else 1.0f)
                        .onGloballyPositioned { coordinates ->
                            positionPizza = coordinates.positionInRoot()
                            sizePizza = coordinates.size

                        }

                )
            }


        }

        randomPepper.forEach {
            AnimatedPepper(it, positionPizza)
        }

        randomCheese.forEach {
            AnimatedCheese(it, positionPizza)
        }

        randomOnion.forEach {
            AnimatedOnion(it, positionPizza)
        }

        randomMeat.forEach {
            AnimatedMeat(it, positionPizza)
        }
        randomTomato.forEach {
            AnimatedTomato(it, positionPizza)
        }


        Card(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(IMAGE_PADDING),
            shape = RoundedCornerShape(
                ROUNDED_CORNER
            )
        ) {

            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .background(Grey1),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                pizzaViewModel.items.forEach { item ->
                    DragTarget(
                        dataToDrop = item,
                        viewModel = pizzaViewModel
                    ) {
                        Image(
                            painterResource(item.icon),
                            contentDescription = "",
                            modifier = Modifier
                                .roundImage()
                                .clickable {
                                    when (item.id) {
                                        ItemId.PEPPER -> {
                                            randomPepper = emptyList()
                                        }

                                        ItemId.ONION -> {
                                            randomOnion = emptyList()
                                        }

                                        ItemId.MEAT -> {
                                            randomMeat = emptyList()
                                        }

                                        ItemId.TOMATO -> {
                                            randomTomato = emptyList()
                                        }

                                        ItemId.CHEESE -> {
                                            randomCheese = emptyList()
                                        }


                                    }
                                }
                                .padding(INNER_IMAGE_PADDING)
                        )
                    }

                }

            }
        }


    }

}


@Composable
fun AnimatedTomato(position: Position, positionPizza: Offset) {
    var forwardAnimation by remember {
        mutableStateOf(true)
    }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    LaunchedEffect(key1 = true) {
        forwardAnimation = !forwardAnimation
    }
    val randomX = Random.nextDouble(0.0, screenWidth.value.toDouble())
    val randomY =
        Random.nextDouble(
            screenHeight.value.toDouble() * 0.25,
            screenHeight.value.toDouble() * 0.75
        )
    val animatedOffset by animateIntOffsetAsState(
        animationSpec = tween(durationMillis = 1000),
        targetValue = if (forwardAnimation) IntOffset(
            randomX.toInt(),
            randomY.toInt()
        ) else IntOffset(
            position.x.dp.value.toInt() + (positionPizza.x.dp.value.toInt() / 2),
            position.y.dp.value.toInt() + (positionPizza.y.dp.value.toInt() / 2) - 80.dp.value.toInt()
        ),
        finishedListener = {

        },
        label = ""
    )
    val animatedScale by animateFloatAsState(
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing),
        targetValue = if (forwardAnimation) 0.5f else 0.6f,
        label = ""
    )

    Image(
        painterResource(R.drawable.tomato),
        contentDescription = "",
        modifier = Modifier
            .offset(animatedOffset.x.dp, animatedOffset.y.dp)
            .scale(animatedScale)

    )
}

@Composable
fun AnimatedMeat(position: Position, positionPizza: Offset) {
    var forwardAnimation by remember {
        mutableStateOf(true)
    }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    LaunchedEffect(key1 = true) {
        forwardAnimation = !forwardAnimation
    }
    val randomX = Random.nextDouble(0.0, screenWidth.value.toDouble())
    val randomY =
        Random.nextDouble(
            screenHeight.value.toDouble() * 0.25,
            screenHeight.value.toDouble() * 0.75
        )
    val animatedOffset by animateIntOffsetAsState(
        animationSpec = tween(durationMillis = 1000),
        targetValue = if (forwardAnimation) IntOffset(
            randomX.toInt(),
            randomY.toInt()
        ) else IntOffset(
            position.x.dp.value.toInt() + (positionPizza.x.dp.value.toInt() / 2),
            position.y.dp.value.toInt() + (positionPizza.y.dp.value.toInt() / 2) - 80.dp.value.toInt()
        ),
        finishedListener = {

        },
        label = ""
    )
    val animatedScale by animateFloatAsState(
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing),
        targetValue = if (forwardAnimation) 0.5f else 0.6f,
        label = ""
    )

    Image(
        painterResource(R.drawable.meat),
        contentDescription = "",
        modifier = Modifier
            .offset(animatedOffset.x.dp, animatedOffset.y.dp)
            .scale(animatedScale)

    )
}

@Composable
fun AnimatedOnion(position: Position, positionPizza: Offset) {
    var forwardAnimation by remember {
        mutableStateOf(true)
    }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    LaunchedEffect(key1 = true) {
        forwardAnimation = !forwardAnimation
    }
    val randomX = Random.nextDouble(0.0, screenWidth.value.toDouble())
    val randomY =
        Random.nextDouble(
            screenHeight.value.toDouble() * 0.25,
            screenHeight.value.toDouble() * 0.75
        )
    val animatedOffset by animateIntOffsetAsState(
        animationSpec = tween(durationMillis = 1000),
        targetValue = if (forwardAnimation) IntOffset(
            randomX.toInt(),
            randomY.toInt()
        ) else IntOffset(
            position.x.dp.value.toInt() + (positionPizza.x.dp.value.toInt() / 2),
            position.y.dp.value.toInt() + (positionPizza.y.dp.value.toInt() / 2) - 80.dp.value.toInt()
        ),
        finishedListener = {

        },
        label = ""
    )
    val animatedScale by animateFloatAsState(
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing),
        targetValue = if (forwardAnimation) 0.5f else 0.6f,
        label = ""
    )

    Image(
        painterResource(R.drawable.onion),
        contentDescription = "",
        modifier = Modifier
            .offset(animatedOffset.x.dp, animatedOffset.y.dp)
            .scale(animatedScale)

    )
}


@Composable
fun AnimatedPepper(position: Position, positionPizza: Offset) {
    var forwardAnimation by remember {
        mutableStateOf(true)
    }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    LaunchedEffect(key1 = true) {
        forwardAnimation = !forwardAnimation
    }
    val randomX = Random.nextDouble(0.0, screenWidth.value.toDouble())
    val randomY =
        Random.nextDouble(
            screenHeight.value.toDouble() * 0.25,
            screenHeight.value.toDouble() * 0.75
        )
    val animatedOffset by animateIntOffsetAsState(
        animationSpec = tween(durationMillis = 1000),
        targetValue = if (forwardAnimation) IntOffset(
            randomX.toInt(),
            randomY.toInt()
        ) else IntOffset(
            position.x.dp.value.toInt() + (positionPizza.x.dp.value.toInt() / 2),
            position.y.dp.value.toInt() + (positionPizza.y.dp.value.toInt() / 2) - 80.dp.value.toInt()
        ),
        finishedListener = {

        },
        label = ""
    )
    val animatedScale by animateFloatAsState(
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing),
        targetValue = if (forwardAnimation) 0.5f else 0.6f,
        label = ""
    )

    Image(
        painterResource(R.drawable.pepper),
        contentDescription = "",
        modifier = Modifier
            .offset(animatedOffset.x.dp, animatedOffset.y.dp)
            .scale(animatedScale)

    )
}


@Composable
fun AnimatedCheese(position: Position, positionPizza: Offset) {
    var forwardAnimation by remember {
        mutableStateOf(true)
    }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    LaunchedEffect(key1 = true) {
        forwardAnimation = !forwardAnimation
    }
    val randomX = Random.nextDouble(0.0, screenWidth.value.toDouble())
    val randomY =
        Random.nextDouble(
            screenHeight.value.toDouble() * 0.25,
            screenHeight.value.toDouble() * 0.75
        )
    val animatedOffset by animateIntOffsetAsState(
        animationSpec = tween(durationMillis = 1000),
        targetValue = if (forwardAnimation) IntOffset(
            randomX.toInt(),
            randomY.toInt()
        ) else IntOffset(
            position.x.dp.value.toInt() + (positionPizza.x.dp.value.toInt() / 2),
            position.y.dp.value.toInt() + (positionPizza.y.dp.value.toInt() / 2) - 80.dp.value.toInt()
        ),
        finishedListener = {

        },
        label = ""
    )
    val animatedScale by animateFloatAsState(
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing),
        targetValue = if (forwardAnimation) 0.5f else 0.6f,
        label = ""
    )

    Image(
        painterResource(R.drawable.cheese),
        contentDescription = "",
        modifier = Modifier
            .offset(animatedOffset.x.dp, animatedOffset.y.dp)
            .scale(animatedScale)

    )
}

