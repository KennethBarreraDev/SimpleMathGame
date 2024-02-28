package com.example.mathgame.screens

import android.widget.Button
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mathgame.R
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OperationsPage(type:String, navController:NavHostController){


    val operation = remember{
        mutableStateOf("+")
    }

    val firstOperatorValue = remember {
        mutableIntStateOf((1..100).random())
    }

    val secondOperatorValue = remember {
        mutableIntStateOf((1..100).random())
    }

    val userResponse = remember{
        mutableStateOf("")
    }

    val userScore = remember{
        mutableIntStateOf(0)
    }

    val lives = remember{
        mutableIntStateOf(3)
    }


    var timeLeft by remember { mutableStateOf(60) }

    LaunchedEffect(key1 = timeLeft) {
        while (timeLeft > 0) {
            delay(1000L)
            timeLeft--
        }
        if(timeLeft==0){
            navController.navigate("Results/${type}/${userScore.value}")
        }
    }



    when(type){
        "Addition" -> operation.value = "+"
        "Subtraction" -> operation.value = "-"
        "Multiplication" -> operation.value = "*"
    }

    Scaffold (
        topBar = {
                 TopAppBar(
                     title = { Text(text = type, modifier = Modifier.padding(horizontal = 20.dp, vertical = 0.dp))},
                     colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF40B2E6)),
                     navigationIcon = { IconButton(onClick = {navController.navigate("Home")}) {
                         Icon(Icons.Outlined.ArrowBack, contentDescription ="")
                     }}
                 )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painterResource(id = R.drawable.background),
                        contentScale = ContentScale.Crop,
                        alpha = 0.8F
                    )
                    .padding(it),
                contentAlignment = Alignment.TopCenter
            )
            {
                // Add more views here!
                Column (Modifier.padding(horizontal = 10.dp, vertical = 0.dp)){
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                       Row{
                           Text(text = "Score: ")
                           Text(text = userScore.value.toString())
                       }
                        Row{
                            Text(text = "Life: ")
                            Text(text = lives.value.toString())
                        }
                        Row{
                            Text(text = "Time: ")
                            Text(text = timeLeft.toString())
                        }
                    }
                    Column(
                        Modifier.verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(Modifier.height(50.dp))
                        TextField(
                            textStyle = LocalTextStyle.current.copy(
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp
                            ),
                            readOnly = true,
                            modifier = Modifier.height(80.dp),
                            value = "${firstOperatorValue.value} ${operation.value} ${secondOperatorValue.value}",
                            onValueChange = { operation.value = it },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color(0xFF50A0C5),
                                textColor = Color.White
                            )
                        )

                        Spacer(Modifier.height(20.dp))
                        TextField(
                            textStyle = LocalTextStyle.current.copy(
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp
                            ),
                            modifier = Modifier.height(80.dp),
                            value = userResponse.value,
                            onValueChange = { userResponse.value = it },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color(0xFF50A0C5),
                                textColor = Color.White
                            ),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        Spacer(Modifier.height(20.dp))

                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 0.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(
                                modifier = Modifier.size(170.dp, 50.dp),
                                onClick = {
                                    var userInputValue = userResponse.value.toInt()

                                    if (type == "Addition") {
                                        if (userInputValue == (firstOperatorValue.value + secondOperatorValue.value)) {
                                            firstOperatorValue.value = (1..100).random()
                                            secondOperatorValue.value = (1..100).random()
                                            userScore.value++
                                            userResponse.value = ""
                                        }
                                        else{
                                            firstOperatorValue.value = (1..100).random()
                                            secondOperatorValue.value = (1..100).random()
                                            lives.value--
                                            userResponse.value = ""
                                        }
                                    } else if (type == "Subtraction") {
                                        if (userInputValue == (firstOperatorValue.value - secondOperatorValue.value)) {
                                            firstOperatorValue.value = (1..100).random()
                                            secondOperatorValue.value = (1..100).random()
                                            userScore.value++
                                            userResponse.value = ""
                                        }
                                        else{
                                            firstOperatorValue.value = (1..100).random()
                                            secondOperatorValue.value = (1..100).random()
                                            lives.value--
                                            userResponse.value = ""
                                        }
                                    } else if (type == "Multiplication") {
                                        if (userInputValue == (firstOperatorValue.value * secondOperatorValue.value)) {
                                            firstOperatorValue.value = (1..100).random()
                                            secondOperatorValue.value = (1..100).random()
                                            userScore.value++
                                            userResponse.value = ""
                                        }
                                        else{
                                            firstOperatorValue.value = (1..100).random()
                                            secondOperatorValue.value = (1..100).random()
                                            lives.value--
                                            userResponse.value = ""
                                        }
                                    }

                                    if(userScore.value==10 || lives.value==0){
                                        navController.navigate("Results/${type}/${userScore.value}")
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White,
                                    contentColor = Color(0xFF50A0C5)
                                ),
                                shape = RoundedCornerShape(20.dp),
                                border = BorderStroke(4.dp, Color(0xFF50A0C5))
                            ) {
                                Text(text = "OK")
                            }

                            Button(
                                modifier = Modifier.size(170.dp, 50.dp),
                                onClick = {
                                    firstOperatorValue.value = (1..100).random()
                                    secondOperatorValue.value = (1..100).random()
                                    userResponse.value = ""
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White,
                                    contentColor = Color(0xFF50A0C5)
                                ),
                                shape = RoundedCornerShape(20.dp),
                                border = BorderStroke(4.dp, Color(0xFF50A0C5))
                            ) {
                                Text(text = "NEXT")
                            }
                        }
                    }
                }
            }
        }
    )
}


