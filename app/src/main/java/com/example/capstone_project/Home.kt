package com.example.capstone_project

import android.app.Activity
import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage


@Preview(showBackground = true)
@Composable
fun Home(navCon: NavHostController = rememberNavController(), menu: List<MenuItem> = emptyList()){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Icon(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "little lemon logo",
                    modifier = Modifier.size(width = 200.dp, height = 40.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(end = 20.dp),
                verticalArrangement = Arrangement.Center,
            ){
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "profile logo",
                    modifier = Modifier
                        .size(60.dp)
                        .align(Alignment.End)
                        .clickable() {
                            navCon.navigate("Profile")
                        }
                )
            }
        }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            var searchPhrase by remember{
                mutableStateOf("")
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF495E57))
            ){
                Text("Little Lemon",
                    style = TextStyle(
                        fontSize = 36.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFFF4CE14),
                        letterSpacing = 1.sp
                    ),
                    modifier = Modifier.padding(start = 10.dp, top = 10.dp)
                )

                Text("Chicago",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFFFFFFFF),
                        letterSpacing = 1.sp
                    ),
                    modifier = Modifier.padding(start = 10.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text("""
                        
                        We are a family owned
                        Mediterranean restaurant, 
                        focused on traditional
                        recipes served with a
                        modern twist.
                    """.trimIndent(),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFFFFFFFF),
                            letterSpacing = 1.sp
                        ),
                        modifier = Modifier.padding(start = 10.dp)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.image3),
                        contentDescription = "home screen display image",
                        modifier = Modifier
                            .size(130.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .padding(end = 10.dp)
                    )
                }

                OutlinedTextField(
                    value = searchPhrase,
                    onValueChange = {
                        searchPhrase = it
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(18.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(17.dp))
                        .background(Color.White),
                    leadingIcon = {
                        Icon(Icons.Default.Search, "search item")
                    },
                    placeholder = {
                        Text("Enter search phrase",
                            style = TextStyle(
                                fontSize = 18.sp
                            )
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                val categories = listOf("Starters", "Mains", "Desserts", "Drinks")
                var currentCategory by remember{
                    mutableStateOf("Starters")
                }

                Text("ORDER FOR DELIVERY!",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, top = 30.dp)
                )

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    items(categories){ category ->
                        Button(
                            onClick = {
                                      currentCategory = category
                            },
                            modifier = Modifier.padding(10.dp),
                            colors = ButtonDefaults.buttonColors(Color(0x3271807B)),
                        ){
                            Text(category,
                                style = TextStyle(
                                    color = Color(0xFF495E57),
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                }

                Divider(
                    modifier = Modifier.padding(18.dp)
                )

                if(searchPhrase.isBlank()){
                    val list = menu.filter{ menuItem ->
                        menuItem.category.lowercase() == currentCategory.lowercase()
                    }
                    MenuItemsComposable(list)
                }
                else{
                    val list = menu.filter{ menuItem ->
                        menuItem.title.lowercase().contains(searchPhrase.lowercase())
                    }
                    MenuItemsComposable(list)
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Preview(showBackground = true)
@Composable
fun MenuItemsComposable(menuItemList: List<MenuItem> = emptyList<MenuItem>()){

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.Center,

    ){
        items(menuItemList){ menuItem ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Column(){
                    Text(menuItem.title,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    )
                    Text(menuItem.description,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color(0xFF525A57)
                        ),
                        modifier = Modifier.width(220.dp)
                    )
                    Spacer(Modifier.height(7.dp))
                    Text("$${menuItem.price}",
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = Color(0xFF525A57),
                            fontWeight = FontWeight.Bold
                        ),
                    )
                }

                GlideImage(
                    model = menuItem.image,
                    contentDescription = "menu item image",
                    modifier = Modifier
                        .size(90.dp)
                        .align(Alignment.Bottom),
                    contentScale = ContentScale.Crop,
                )
            }
            Divider(
                modifier = Modifier.padding(top = 18.dp)
            )
        }
    }
}









