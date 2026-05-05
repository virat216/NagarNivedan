package com.example.nagarnivedan.model

data class LoginResponse(
    val message: String,
    val User: User
)

data class User(
    val id: String,
    val email: String,
    val username: String,
    val role: String
)