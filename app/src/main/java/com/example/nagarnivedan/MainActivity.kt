package com.example.nagarnivedan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.nagarnivedan.navigation.AppNavigation
import com.example.nagarnivedan.network.RetrofitClient
import com.example.nagarnivedan.ui.theme.NagarNivedanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 🔥 VERY IMPORTANT — initialize Retrofit with context
        RetrofitClient.init(applicationContext)

        enableEdgeToEdge()

        setContent {
            NagarNivedanTheme {
                AppNavigation()
            }
        }
    }
}
