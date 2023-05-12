package com.moracoding.firebaseexp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.moracoding.firebaseexp.presentation.navigation.NavigationGraph
import com.moracoding.firebaseexp.presentation.ui.theme.FirebaseExpTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseExpTheme {
                NavigationGraph()
            }
        }
    }
}