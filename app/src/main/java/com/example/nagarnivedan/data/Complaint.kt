package com.example.nagarnivedan.data

data class Complaint(
    val id: String,
    val title: String,
    val description: String,
    val status: String,
    val priority: String? = null,
    val category: String? = null
)