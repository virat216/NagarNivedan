package com.example.nagarnivedan.network

import android.content.Context
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://nagar-nivedan-backend.onrender.com/api/"

    private lateinit var cookieJar: PersistentCookieJar
    lateinit var apiService: ApiService

    fun init(context: Context) {

        cookieJar = PersistentCookieJar(context)

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .cookieJar(cookieJar)   // 🔥 persistent cookies
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    fun clearSession() {
        cookieJar.clear()
    }
}