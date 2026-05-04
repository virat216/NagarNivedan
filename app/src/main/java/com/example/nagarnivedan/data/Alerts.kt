package com.example.nagarnivedan.data

data class Alert(
    val id: String,
    val title: String,
    val location: String,
    val time: String,
    val description: String,
    val type: String,
    var isRead: Boolean = false
)