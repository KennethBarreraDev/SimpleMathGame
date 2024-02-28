package com.example.mathgame.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun  ResultPage(navController: NavHostController, type: String, score: Int){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.blue_background),
                contentScale = ContentScale.Crop,
                alpha = 0.8F
            ),
        contentAlignment = Alignment.TopCenter
    )
    {
        // Add more views here!
        Column(
            Modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(40.dp))
            Text(text = "Congratulations!", fontSize = 40.sp, color = Color.White)
            Spacer(Modifier.height(40.dp))
            Text(text = "Your Score", fontSize = 20.sp, color = Color.White)
            Spacer(Modifier.height(10.dp))
            Text(text = score.toString(), fontSize = 20.sp, color = Color.White)
            Spacer(Modifier.height(40.dp))
            Row{
                Button(
                    modifier = Modifier.size(170.dp, 50.dp),
                    onClick = { navController.navigate("Operations/${type}") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFF50A0C5)
                    ),
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(4.dp, Color(0xFF50A0C5))
                ) {
                    Text(text = "PLAY AGAIN")
                }
                Spacer(Modifier.height(20.dp))
                Button(
                    modifier = Modifier.size(170.dp, 50.dp),
                    onClick = {navController.navigate("Home")},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFF50A0C5)
                    ),
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(4.dp, Color(0xFF50A0C5))
                ) {
                    Text(text = "EXIT")
                }
            }
        }
    }
}