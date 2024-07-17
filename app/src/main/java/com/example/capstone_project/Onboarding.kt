package com.example.capstone_project

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview(showBackground = true)
@Composable
fun OnBoarding(){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .height(100.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Icon(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "little lemon logo",
                    modifier = Modifier.padding(10.dp)
                        .height(45.dp)
                )
            }
        }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Box(
                modifier = Modifier.height(110.dp)
                    .fillMaxWidth()
                    .background(Color(0xD5213820)),
                contentAlignment = Alignment.Center
            ){
                Text("Let's get to know you",
                    style = TextStyle(
                        fontSize = 23.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    ),
                )
            }

            Box(
                modifier = Modifier.fillMaxWidth()
                    .height(120.dp),
                contentAlignment = Alignment.CenterStart
            ){
                Text("Personal information",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    ),
                    modifier = Modifier.padding(10.dp)
                    )
            }

            var firstName by remember{
                mutableStateOf("")
            }
            var lastName by remember{
                mutableStateOf("")
            }
            var email by remember{
                mutableStateOf("")
            }

            Column() {
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { value ->
                        firstName = value
                    },
                    label = {
                        Text("First name")
                    },
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp)
                )

                Spacer(Modifier.height(45.dp))

                OutlinedTextField(
                    value = lastName,
                    onValueChange = { value ->
                        lastName = value
                    },
                    label = {
                        Text("Last name")
                    },
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp)
                )

                Spacer(Modifier.height(45.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { value ->
                        email = value
                    },
                    label = {
                        Text("Email")
                    },
                    textStyle = TextStyle(
                        color = Color.Black
                    ),
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp)
                )
            }

            Spacer(Modifier.height(160.dp))

            Button(
                onClick = {
                    if(areValidCred(firstName, lastName, email)){

                    }
                    else{

                    }
                },
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                shape = RoundedCornerShape(7.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFCFCD23B)),
                border = BorderStroke(1.dp, Color(0xFCC59D0D))
            ){
                Text("Register",
                    color = Color.Black,
                    fontSize = 16.sp
                    )
            }
        }
    }
}

fun areValidCred(firstName: String, lastName: String, email: String): Boolean{
    if(firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank()){
        if(email.endsWith(".com")){
            return true;
        }
    }
    return false
}