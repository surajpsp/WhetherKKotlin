package com.silentdev.whetherkmm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.Navigator
import com.silentdev.whetherkmm.persentation.OnboardingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Navigator(screen = OnboardingScreen())
        }

//        setContent {
//            App()
//        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    OnboardingScreen()
}