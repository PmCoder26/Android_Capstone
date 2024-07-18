package com.example.capstone_project

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Preview(showBackground = true)
@Composable
fun Profile(navCon: NavHostController = rememberNavController(), activity: Activity? = null){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                horizontalArrangement = Arrangement.Center
            ){
                Icon(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "little lemon logo",
                    modifier = Modifier
                        .padding(top = 20.dp)
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
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                contentAlignment = Alignment.CenterStart
            ){
                Text("Personal information",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    ),
                    modifier = Modifier.padding(15.dp)
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .size(300.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ){

                    val sharedPref = activity!!.getSharedPreferences("User", MODE_PRIVATE)
                    val firstName = sharedPref.getString("firstName", "First name")
                    val lastName = sharedPref.getString("lastName", "Last name")
                    val email = sharedPref.getString("email", "Email")


                    Text("First name",
                        modifier = Modifier.padding(top = 15.dp)
                        )
                    Text(firstName!!,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp)
                            .border(
                                border = BorderStroke(1.dp, Color(0x6F717575)),
                                shape = RoundedCornerShape(7.dp)
                            )
                            .padding(start = 15.dp, top = 10.dp),
                        style = TextStyle(
                            fontSize = 18.sp,
                        ),
                        )

                    Text("Last name",
                        modifier = Modifier.padding(top = 15.dp)
                    )
                    Text(lastName!!,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp)
                            .border(
                                border = BorderStroke(1.dp, Color(0x6F717575)),
                                shape = RoundedCornerShape(7.dp)
                            )
                            .padding(start = 15.dp, top = 10.dp),
                        style = TextStyle(
                            fontSize = 18.sp,
                        ),
                    )

                    Text("Email",
                        modifier = Modifier.padding(top = 15.dp)
                    )
                    Text(email!!,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp)
                            .border(
                                border = BorderStroke(1.dp, Color(0x6F717575)),
                                shape = RoundedCornerShape(7.dp)
                            )
                            .padding(start = 15.dp, top = 10.dp),
                        style = TextStyle(
                            fontSize = 18.sp,
                        ),
                    )
                }
            }

            Spacer(Modifier.height(200.dp))

            Button(
                onClick = {
                    try{
                        val sharedPref = activity!!.getSharedPreferences("User", MODE_PRIVATE)
                        val areCleared = sharedPref.edit().clear().commit()
                        if(areCleared){
                            Toast.makeText(activity.applicationContext, "Logged out successfully!", Toast.LENGTH_LONG).show()
                            navCon.navigate("OnBoarding")
                        }
                    } catch(e: Exception){
                        Toast.makeText(activity!!.applicationContext, e.message.toString(), Toast.LENGTH_LONG).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                shape = RoundedCornerShape(7.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFF5CF14)),
                border = BorderStroke(1.dp, Color(0xFCC59D0D))
            ){
                Text("Log out",
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }

        }
    }
}