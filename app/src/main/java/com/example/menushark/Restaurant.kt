package com.example.menushark


data class Restaurant(
    val id: String,
    val name: String,
    val score: String,
    val zipCode: String,
    val city: String,
    val state: String,
    var menus: List<Menu> = emptyList()
)
