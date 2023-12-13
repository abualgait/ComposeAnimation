package com.testing.composeanimationplayground.ui.pizza


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.testing.composeanimationplayground.R
import com.testing.composeanimationplayground.ui.IMAGE_PADDING
import com.testing.composeanimationplayground.ui.INNER_IMAGE_PADDING
import com.testing.composeanimationplayground.ui.ROUNDED_CORNER
import com.testing.composeanimationplayground.ui.roundImage
import com.testing.composeanimationplayground.ui.theme.Grey1


@Composable
fun PizzaAnimationScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
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
                        .padding(INNER_IMAGE_PADDING)
                )
            }
        }


    }
}


