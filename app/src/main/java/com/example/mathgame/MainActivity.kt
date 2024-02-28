package com.example.mathgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mathgame.screens.HomeScreen
import com.example.mathgame.screens.OperationsPage
import com.example.mathgame.screens.ResultPage
import com.example.mathgame.ui.theme.MathGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MathGameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navigator()
                }
            }
        }
    }
}

@Composable
fun navigator(){
    val navController = rememberNavController();

    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            HomeScreen(navController)
        }
        composable("Operations/{type}", arguments = listOf(navArgument("type"){type = NavType.StringType})){ arguments ->
            val type: String = arguments.arguments?.getString("type")!!
            OperationsPage(type, navController)
        }
        composable(
            "Results/{type}/{score}",
            arguments = listOf(navArgument("type"){type = NavType.StringType}, navArgument("score"){type = NavType.IntType})
        ){
            val type: String = it.arguments?.getString("type")!!
            val score: Int = it.arguments?.getInt("score")!!
            ResultPage(navController = navController, type, score)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MathGameTheme {

    }
}