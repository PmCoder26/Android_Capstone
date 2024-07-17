package com.example.capstone_project

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@SerialName("MenuNetworkdata")
data class MenuNetworkdata(
    val menu: MutableList<MenuItemNetwork> = mutableListOf()
)


@Serializable
@SerialName("MenuItemNetwork")
data class MenuItemNetwork(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
)
