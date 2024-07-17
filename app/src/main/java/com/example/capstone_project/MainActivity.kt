package com.example.capstone_project

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.capstone_project.ui.theme.Capstone_ProjectTheme
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {

    private val database by lazy{
        Room.databaseBuilder(
            this@MainActivity,
            MenuItemDatabase::class.java,
            "MenuItemsDB"
        ).build()
    }

    private val viewModel by viewModels<MenuItemViewModel> {
        ViewModelFactory(this@MainActivity, database.menuItemDao)
    }

    private val baseUrl = "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"

    private val client by lazy{
        HttpClient(Android){
            install(ContentNegotiation){
                json(Json{
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
    }

    private var apiResponse = MenuNetworkdata()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch{
            try {
                getMenuItems()
                storeMenuItems()
            } catch(e: Exception){
                toast(e.message.toString())
                println(e.message)
            }
        }

        setContent {
            Capstone_ProjectTheme {
                MyContent()
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun MyContent(){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            val menuList by viewModel.menuItems.collectAsState()
            LazyColumn(){
                items(menuList){ menuItem ->
                    Text(menuItem.title)
                }
            }
        }
    }

    private fun toast(text: String){
        Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show()
    }

    private suspend fun getMenuItems() {
        val responseBody = client.get(urlString = baseUrl).bodyAsText()
        val gson = Gson()
        apiResponse = gson.fromJson(responseBody, MenuNetworkdata::class.java)
        if (apiResponse.menu.isNotEmpty()) {
            toast("Menu items received by api calls!")
        } else {
            toast("Failed to receive menu items!")
        }
    }

    private suspend fun storeMenuItems() {
        apiResponse.menu.forEach { menuItem ->
            viewModel.addMenuItem(
                MenuItem(
                    id = menuItem.id,
                    title = menuItem.title,
                    price = menuItem.price,
                    image = menuItem.image,
                    category = menuItem.category,
                    description = menuItem.description
                )
            )
        }
    }
}
