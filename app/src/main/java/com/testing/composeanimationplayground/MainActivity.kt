package com.testing.composeanimationplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.testing.composeanimationplayground.ui.MyApp
import com.testing.composeanimationplayground.ui.theme.ComposeAnimationPlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAnimationPlaygroundTheme {
                MyApp()
            }
        }
    }
}

