package com.example.capstone_project

import android.app.Activity
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class MenuItemViewModel(
    private val activity: Activity?, private val menuItemDao: MenuItemDao?
) : ViewModel() {


    val menuItems = menuItemDao?.getAllMenuItems()!!
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun addMenuItem(menuItem: MenuItem){
        viewModelScope.launch {
            try{
                menuItemDao?.insertItem(menuItem)
            } catch (e: Exception){
                toast(e.message.toString())
            }
        }
    }


    private fun toast(text: String){
        Toast.makeText(activity?.applicationContext, text, Toast.LENGTH_LONG).show()
    }

}