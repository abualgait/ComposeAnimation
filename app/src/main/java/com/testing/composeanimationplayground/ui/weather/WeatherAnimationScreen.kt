package com.testing.composeanimationplayground.ui.weather


import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.selection.toggleable
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.testing.composeanimationplayground.R
import com.testing.composeanimationplayground.ui.IMAGE_PADDING
import com.testing.composeanimationplayground.ui.INNER_IMAGE_PADDING
import com.testing.composeanimationplayground.ui.Position
import com.testing.composeanimationplayground.ui.ROUNDED_CORNER
import com.testing.composeanimationplayground.ui.generateRandomPositions
import com.testing.composeanimationplayground.ui.roundImage
import com.testing.composeanimationplayground.ui.theme.Blue400
import com.testing.composeanimationplayground.ui.theme.Grey1
import kotlinx.coroutines.delay
import kotlin.random.Random


@Composable
fun WeatherAnimationScreen() {
    var animateRainy by remember { mutableStateOf(false) }
    var animateSunny by remember { mutableStateOf(false) }
    var animateSunnyAndCloudy by remember { mutableStateOf(false) }
    var animateNightAndCloudy by remember { mutableStateOf(false) }
    var animateNight by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        if (animateSunny) {
            SunnyAnimation(Modifier.fillMaxSize())
        }

        if (animateNight) {
            NightAnimation(Modifier.fillMaxSize())
        }

        if (animateRainy) {
            RainyAnimation(Modifier.fillMaxSize())
        }

        if (animateSunnyAndCloudy) {
            SunnyAnimation(Modifier.fillMaxSize())
            CloudyAnimation(Modifier.fillMaxSize())
        }

        if (animateNightAndCloudy) {
            NightAnimation(Modifier.fillMaxSize())
            CloudyAnimation(Modifier.fillMaxSize())
        }



        Card(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(IMAGE_PADDING),
            shape = RoundedCornerShape(
                ROUNDED_CORNER
            )
        ) {
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .background(Grey1),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Image(
                    painterResource(R.drawable.rainy),
                    contentDescription = "",
                    modifier = Modifier
                        .roundImage()
                        .background(if (animateRainy) Blue400 else Color.Transparent)

                        .toggleable(
                            value = animateRainy,
                            onValueChange = {
                                animateRainy = !animateRainy
                            }
                        )
                        .padding(INNER_IMAGE_PADDING)
                )
                Image(
                    painterResource(R.drawable.sunny),
                    contentDescription = "",
                    modifier = Modifier
                        .roundImage()
                        .background(if (animateSunny) Blue400 else Color.Transparent)

                        .toggleable(
                            value = animateSunny,
                            onValueChange = {
                                animateNight = false
                                animateSunny = !animateSunny
                            }
                        )
                        .padding(INNER_IMAGE_PADDING)
                )
                Image(
                    painterResource(R.drawable.sunny_night),
                    contentDescription = "",
                    modifier = Modifier
                        .roundImage()
                        .background(if (animateNight) Blue400 else Color.Transparent)

                        .toggleable(
                            value = animateNight,
                            onValueChange = {
                                animateSunny = false
                                animateRainy = false
                                animateNight = !animateNight
                            }
                        )
                        .padding(INNER_IMAGE_PADDING)
                )
                Image(
                    painterResource(R.drawable.cloudy),
                    contentDescription = "",
                    modifier = Modifier
                        .roundImage()
                        .background(if (animateSunnyAndCloudy) Blue400 else Color.Transparent)

                        .toggleable(
                            value = animateSunnyAndCloudy,
                            onValueChange = {
                                animateRainy = false
                                animateNight = false
                                animateNightAndCloudy = false
                                animateSunny = false
                                animateSunnyAndCloudy = !animateSunnyAndCloudy
                            }
                        )
                        .padding(INNER_IMAGE_PADDING)
                )


                Image(
                    painterResource(R.drawable.cloudy_night),
                    contentDescription = "",
                    modifier = Modifier
                        .roundImage()
                        .background(if (animateNightAndCloudy) Blue400 else Color.Transparent)
                        .toggleable(
                            value = animateNightAndCloudy,
                            onValueChange = {
                                animateRainy = false
                                animateNight = false
                                animateSunnyAndCloudy = false
                                animateSunny = false
                                animateNightAndCloudy = !animateNightAndCloudy
                            }
                        )
                        .padding(INNER_IMAGE_PADDING)
                )
            }
        }


    }
}

@Composable
fun RainyAnimation(modifier: Modifier) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp


    var randomXYPositions by remember {
        mutableStateOf(listOf<Position>())
    }
    var randomXYPositionsFromBottom by remember {
        mutableStateOf(listOf<Position>())
    }

    var randomFlowerXYPositionsFromBottom by remember {
        mutableStateOf(listOf<Position>())
    }

    LaunchedEffect(key1 = true) {
        randomXYPositions =
            generateRandomPositions(
                10,
                screenWidth.value.toInt(),
                (screenHeight.value * 0.30f).toInt()
            )
        delay(1000)
        randomXYPositionsFromBottom =
            generateRandomPositions(
                numPositions = 10,
                width = screenWidth.value.toInt(),
                height = ((screenHeight - 80.dp).value * 0.30f).toInt()
            )

        randomFlowerXYPositionsFromBottom =
            generateRandomPositions(
                numPositions = 10,
                width = screenWidth.value.toInt(),
                height = ((screenHeight - 80.dp).value * 0.30f).toInt()
            )


    }

    Box(modifier.height(screenHeight - 80.dp)) {
        randomXYPositions.forEach {
            AnimatedRainy(position = it)
        }

        randomXYPositionsFromBottom.forEach {
            AnimatedGrass(position = it)
        }
        randomFlowerXYPositionsFromBottom.forEach {
            AnimatedFlower(position = it)
        }

    }

}

@Composable
fun CloudyAnimation(modifier: Modifier) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val randomXYPositions =
        generateRandomPositions(
            3,
            screenWidth.value.toInt(),
            (screenHeight.value * 0.20f).toInt()
        )

    Box(modifier) {
        randomXYPositions.forEach {
            AnimatedCloudy(position = it)
        }
    }

}

@Composable
fun SunnyAnimation(modifier: Modifier) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    var forwardAnimation by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = true) {
        delay(100)
        forwardAnimation = !forwardAnimation
    }
    Box(
        modifier.background(
            brush = Brush.verticalGradient(
                listOf(
                    Color(0xFFFFEB3B),
                    Color(0xFFFFFFFF),
                )
            )
        )
    ) {
        // Canvas with Arc
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .height((screenHeight - 80.dp) / 2)
        ) {
            val width = size.width
            val height = size.height

            drawCircle(
                brush = Brush.linearGradient(listOf(Color(0xFFA8E6CE), Color(0xFFDCEDC2))),
                (width + 300) / 2,
                center = Offset(width / 2, height)
            )
            drawCircle(
                brush = Brush.linearGradient(listOf(Color(0xFFB2DBBF), Color(0xFF6AAB9C))),
                width / 2,
                center = Offset(0f, height)
            )

            drawCircle(
                brush = Brush.linearGradient(listOf(Color(0xFF9BC53D), Color(0xFF5D8233))),
                (width + 200) / 2,
                center = Offset(width, height)
            )


            drawCircle(
                brush = Brush.linearGradient(listOf(Color(0xFF556B2F), Color(0xFF8F9779))),
                (width + 300) / 2,
                center = Offset(width / 2, height + 100)
            )
            drawCircle(
                brush = Brush.linearGradient(listOf(Color(0xFF8AFF86), Color(0xFF42A74D))),
                width / 2,
                center = Offset(0f, height + 100)
            )

            drawCircle(
                brush = Brush.linearGradient(listOf(Color(0xFF7E9F5B), Color(0xFF44593D))),
                (width + 200) / 2,
                center = Offset(width, height + 300)
            )

        }

        val animatedOffset by animateIntOffsetAsState(
            animationSpec = tween(durationMillis = 1000),
            targetValue = if (forwardAnimation) IntOffset.Zero else IntOffset(
                70,
                70
            ),
            finishedListener = {

            },
            label = ""
        )
        Image(
            painterResource(R.drawable.sunny),
            contentDescription = "",
            modifier = Modifier
                .offset(animatedOffset.x.dp, animatedOffset.y.dp)
                .scale(1.5f)

        )
    }

}

@Composable
fun NightAnimation(modifier: Modifier) {

    var forwardAnimation by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = true) {
        delay(100)
        forwardAnimation = !forwardAnimation
    }
    Box(
        modifier.background(
            brush = Brush.verticalGradient(
                listOf(
                    Color(0xBA191970),
                    Color(0xE8000033)
                )
            )
        )
    ) {
        val animatedOffset by animateIntOffsetAsState(
            animationSpec = tween(durationMillis = 1000),
            targetValue = if (forwardAnimation) IntOffset.Zero else IntOffset(
                70,
                70
            ),
            finishedListener = {

            },
            label = ""
        )
        Image(
            painterResource(R.drawable.sunny_night),
            contentDescription = "",
            modifier = Modifier
                .offset(animatedOffset.x.dp, animatedOffset.y.dp)
                .scale(1.5f)

        )
    }

}

@Composable
fun AnimatedRainy(position: Position) {
    var forwardAnimation by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = true) {
        delay(100)
        forwardAnimation = !forwardAnimation
    }
    val animatedOffset by animateIntOffsetAsState(
        animationSpec = tween(durationMillis = 1000),
        targetValue = if (forwardAnimation) IntOffset(position.x, 0) else IntOffset(
            position.x,
            position.y
        ),
        finishedListener = {

        },
        label = ""
    )
    val animatedScale by animateFloatAsState(
        animationSpec = tween(durationMillis = 1000),
        targetValue = if (forwardAnimation) Random.nextDouble(1.0).toFloat() else Random.nextDouble(
            1.5
        ).toFloat(),
        label = ""
    )

    Image(
        painterResource(R.drawable.rainy),
        contentDescription = "",
        modifier = Modifier
            .offset(animatedOffset.x.dp, animatedOffset.y.dp)
            .scale(animatedScale)

    )
}

@Composable
fun AnimatedGrass(position: Position) {
    var forwardAnimation by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = true) {
        delay(100)
        forwardAnimation = !forwardAnimation
    }
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp


    val animatedOffset by animateIntOffsetAsState(
        animationSpec = tween(durationMillis = 1000),
        targetValue = if (forwardAnimation) IntOffset(
            position.x,
            screenHeight.value.toInt()
        ) else IntOffset(
            position.x,
            screenHeight.value.toInt() - position.y.dp.value.toInt() - 100.dp.value.toInt()
        ),
        finishedListener = {

        },
        label = ""
    )
    val animatedScale by animateFloatAsState(
        animationSpec = tween(durationMillis = 1000),
        targetValue = if (forwardAnimation) Random.nextDouble(1.0).toFloat() else Random.nextDouble(
            1.5
        ).toFloat(),
        label = ""
    )

    Image(
        painterResource(R.drawable.grass),
        contentDescription = "",
        modifier = Modifier
            .size(100.dp)
            .offset(animatedOffset.x.dp, animatedOffset.y.dp)
            .scale(animatedScale)

    )
}


@Composable
fun AnimatedFlower(position: Position) {
    var forwardAnimation by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = true) {
        delay(100)
        forwardAnimation = !forwardAnimation
    }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp


    val animatedOffset by animateIntOffsetAsState(
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing),
        targetValue = if (forwardAnimation) IntOffset(
            position.x,
            screenHeight.value.toInt()
        ) else IntOffset(
            position.x,
            screenHeight.value.toInt() - position.y.dp.value.toInt() - 150.dp.value.toInt()
        ),
        finishedListener = {

        },
        label = ""
    )
    val animatedScale by animateFloatAsState(
        animationSpec = tween(durationMillis = 1000),
        targetValue = if (forwardAnimation) Random.nextDouble(1.0).toFloat() else Random.nextDouble(
            1.5
        ).toFloat(),
        label = ""
    )

    Image(
        painterResource(R.drawable.flower),
        contentDescription = "",
        modifier = Modifier
            .size(150.dp)
            .offset(animatedOffset.x.dp, animatedOffset.y.dp)
            .scale(animatedScale)

    )
}


@Composable
fun AnimatedCloudy(position: Position) {
    var forwardAnimation by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = true) {
        delay(100)
        forwardAnimation = !forwardAnimation
    }
    val animatedOffset by animateIntOffsetAsState(
        animationSpec = tween(durationMillis = 1000),
        targetValue = if (forwardAnimation) IntOffset(position.x, 0) else IntOffset(
            position.x,
            position.y
        ),
        finishedListener = {

        },
        label = ""
    )
    val animatedScale by animateFloatAsState(
        animationSpec = tween(durationMillis = 1000),
        targetValue = if (forwardAnimation) Random.nextDouble(1.0).toFloat() else Random.nextDouble(
            2.5
        ).toFloat(),
        label = ""
    )

    Image(
        painterResource(R.drawable.clouds),
        contentDescription = "",
        modifier = Modifier
            .offset(animatedOffset.x.dp, animatedOffset.y.dp)
            .scale(animatedScale)

    )
}

 