package com.example.mathgame.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mathgame.R

@Composable
fun HomeScreen(navController: NavHostController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.clouds),
                contentScale = ContentScale.Crop,
                alpha = 0.4F
            ),
        contentAlignment = Alignment.Center
    )
    {
        // Add more views here!
        Column(
            Modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Welcome!", color = Color.White, fontSize = 30.sp)
            Spacer(Modifier.height(30.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(
                    0xFF0D8A7E,
                    ),
                    contentColor = Color.White
                ),
                onClick = {navController.navigate("Operations/Addition")},
                contentPadding = PaddingValues(vertical = 30.dp, horizontal = 120.dp),
                shape = RectangleShape
            ) {
                Text(text = "ADDITION")
            }
            Spacer(Modifier.height(30.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(
                        0xFF0D8A7E,
                    ),
                    contentColor = Color.White
                ),
                onClick = {navController.navigate("Operations/Subtraction")},
                contentPadding = PaddingValues(vertical = 30.dp, horizontal = 100.dp),
                shape = RectangleShape
            ) {
                Text(text = "SUBTRACTION")
            }
            Spacer(Modifier.height(30.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(
                        0xFF0D8A7E,
                    ),
                    contentColor = Color.White
                ),
                onClick = {navController.navigate("Operations/Multiplication")},
                contentPadding = PaddingValues(vertical = 30.dp, horizontal = 100.dp),
                shape = RectangleShape
            ) {
                Text(text = "MULTIPLICATION")
            }
        }
    }
}