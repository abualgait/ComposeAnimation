package com.testing.composeanimationplayground.ui.daynight


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.testing.composeanimationplayground.R
import com.testing.composeanimationplayground.ui.Position
import com.testing.composeanimationplayground.ui.generateRandomPositions


@Composable
fun DayNightAnimationScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        var progress by remember {
            mutableFloatStateOf(0f)
        }
        DayNight(progress = progress)

        Slider(
            value = progress,
            onValueChange = {
                progress = it
            },
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .align(Alignment.BottomCenter)
        )
    }
}


@OptIn(ExperimentalMotionApi::class, ExperimentalMotionApi::class)
@Composable
fun DayNight(progress: Float) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    var positions by remember {
        mutableStateOf(listOf<Position>())
    }

    LaunchedEffect(key1 = true) {
        positions = generateRandomPositions(
            100,
            screenWidth.value.toInt() * 4,
            screenHeight.value.toInt()
        )
    }


    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }

    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier.fillMaxSize()
    ) {
        val propertiesBox = motionProperties(id = "box")
        val propertiesSunMoon = motionProperties(id = "sun_moon")
        val streetLamp = motionProperties(id = "street_lamp")

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            propertiesBox.value.color("background"),
                            propertiesBox.value.color("background_end"),
                        )
                    )
                )
                .layoutId("box")
        )
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .layoutId("stars")
        ) {
            drawStars(positions)
        }

        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(color = propertiesSunMoon.value.color("background"))
                .layoutId("sun_moon")
        )

        Image(
            painter = painterResource(id = R.drawable.mountain),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.layoutId("mountain")
        )

        Image(
            painter = painterResource(id = R.drawable.asphalt_road),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.layoutId("road")
        )
        Image(
            painter = painterResource(id = R.drawable.tree),
            contentDescription = null,
            modifier = Modifier
                .layoutId("tree")
        )

        Box(
            modifier = Modifier
                .layoutId("street_lamp")
        ) {
            Image(
                painter = painterResource(id = R.drawable.street_lamp),
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .align(Alignment.BottomCenter)
            )

            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .clip(CircleShape)
                    .size(100.dp)
                    .background(
                        brush = Brush.radialGradient(
                            listOf(
                                streetLamp.value.color("lamp_light"),
                                Color.Transparent,
                            )
                        )
                    )
            )

        }


        Image(
            painter = painterResource(id = R.drawable.car_q3),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .layoutId("car")
        )

        Image(
            painter = painterResource(id = R.drawable.birds),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .layoutId("birds")
        )


        Image(
            painter = painterResource(id = R.drawable.clouds),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .layoutId("cloud")
        )


    }
}


fun DrawScope.drawStars(stars: List<Position>) {
    stars.forEach { star ->
        drawCircle(
            color = Color.White,
            center = Offset(star.x.dp.value, star.y.dp.value),
            radius = 1.dp.toPx()
        )
    }
}

