package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Calculator()
        }
    }
}

@Composable
fun Calculator(modifier: Modifier = Modifier){
    var input by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var calculated by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom
    ){
        Text(
            text = input,
            fontSize = 36.sp,
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Text(
            text = result,
            fontSize = 36.sp,
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
        ){
            Column (
                modifier = Modifier
                    .weight(3f)
                    .background(Color.DarkGray)
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                val numberButtons = listOf(
                    listOf("7", "8", "9"),
                    listOf("4", "5", "6"),
                    listOf("1", "2", "3"),
                    listOf(".", "0", "=")
                )

                numberButtons.forEach(){ row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ){
                        row.forEach(){ number ->
                            Button(
                                onClick = {
                                    when(number){
                                        "=" -> {
                                            //result = calculateResult(input)
                                            calculated = true
                                        }
                                        else -> {
                                            if(calculated){
                                                input = number
                                                calculated = false
                                            }else{
                                                input += number
                                            }
                                        }
                                    }
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(1f),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Gray
                                )
                            ) {
                                Text(text = number, fontSize = 24.sp, color = Color.White)
                            }
                        }
                    }
                }
            }
            Column (
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Gray)
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                val operations = listOf("DEL", "÷", "×", "-", "+", "AC")

                operations.forEach(){ operation ->
                    Button(
                        onClick = {
                            when(operation){
                                "DEL" -> {
                                    if(input.isNotEmpty() && !calculated){
                                        input = input.dropLast(1)
                                    }else if(calculated){
                                        input = ""
                                        calculated = false
                                    }
                                }
                                "AC" -> {
                                        input = ""
                                }
                                else -> {
                                    if(calculated){
                                        input = result + operation
                                        calculated = false
                                    }else{
                                        input += operation
                                    }
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF00D6D6)
                        )
                    ){
                      Text(operation, fontSize = 24.sp, color = Color.White)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCalculator(){
    Calculator()
}

