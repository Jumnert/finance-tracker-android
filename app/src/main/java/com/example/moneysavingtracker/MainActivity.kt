package com.example.moneysavingtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.moneysavingtracker.ui.screens.NavGraph
import com.example.moneysavingtracker.ui.theme.MoneySavingTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoneySavingTrackerTheme {
                NavGraph()
            }
        }
    }
}
