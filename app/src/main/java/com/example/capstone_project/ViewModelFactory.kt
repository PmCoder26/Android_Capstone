package com.example.capstone_project

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(
    private val activity: Activity, private val menuItemDao: MenuItemDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MenuItemViewModel(activity, menuItemDao) as T
    }
}