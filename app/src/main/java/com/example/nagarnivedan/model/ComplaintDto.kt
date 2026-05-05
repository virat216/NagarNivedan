package com.example.nagarnivedan.model

data class ComplaintDto(
    val id: String? = null,
    val title: String? = null,
    val description: String? = null,
    val status: String? = null,
    val priority: String? = null,
    val category: String? = null,
    val location: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val images: List<String>? = emptyList()
)