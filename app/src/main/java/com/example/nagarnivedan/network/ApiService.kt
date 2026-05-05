package com.example.nagarnivedan.network

import com.example.nagarnivedan.model.CreateComplaintResponse
import com.example.nagarnivedan.model.LoginRequest
import com.example.nagarnivedan.model.LoginResponse
import com.example.nagarnivedan.model.RegisterRequest
import com.example.nagarnivedan.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import com.example.nagarnivedan.model.MyComplaintsResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.Part
import com.example.nagarnivedan.model.AlertDto
import com.example.nagarnivedan.model.AlertsResponse
import com.example.nagarnivedan.model.ComplaintDto
import com.example.nagarnivedan.model.ProfileResponse
import com.example.nagarnivedan.model.UserDto
import retrofit2.http.Path

interface ApiService {

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @GET("auth/me")
    suspend fun getProfile(): Response<ProfileResponse>

    @GET("complaints/{id}")
    suspend fun getComplaintById(
        @Path("id") id: String
    ): Response<ComplaintDto>

    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<RegisterResponse>

    @GET("complaints/me")
    suspend fun getMyComplaints(): Response<MyComplaintsResponse>

    @Multipart
    @POST("complaints")
    suspend fun createComplaint(
        @Part("category") category: RequestBody,
        @Part("description") description: RequestBody,
        @Part("location") location: RequestBody,
        @Part photos: List<MultipartBody.Part>
    ): Response<CreateComplaintResponse>

    @GET("alerts")
    suspend fun getAlerts(): Response<AlertsResponse>

    @GET("alerts/{id}")
    suspend fun getAlertById(
        @Path("id") id: String
    ): Response<AlertDto>

}