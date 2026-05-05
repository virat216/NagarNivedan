package com.example.nagarnivedan.model
import com.google.gson.annotations.SerializedName
data class MyComplaintsResponse(
    val success: Boolean,
    val data: List<ComplaintDto>
)

