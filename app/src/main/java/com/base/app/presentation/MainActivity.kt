package com.base.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.base.app.intent.AppNavigation
import com.base.app.presentation.theme.BaseApplicationTheme
import com.base.app.presentation.theme.darkBlue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaseApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = darkBlue
                ) { AppNavigation() }
            }
        }
    }
}