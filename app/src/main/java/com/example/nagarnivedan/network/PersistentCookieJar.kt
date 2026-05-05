package com.example.nagarnivedan.network

import android.content.Context
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class PersistentCookieJar(context: Context) : CookieJar {

    private val prefs = context.getSharedPreferences("cookies", Context.MODE_PRIVATE)

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        val encoded = cookies.joinToString(";") { it.toString() }
        prefs.edit().putString(url.host, encoded).apply()
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val stored = prefs.getString(url.host, null) ?: return emptyList()
        return stored.split(";").mapNotNull { Cookie.parse(url, it) }
    }

    fun clear() {
        prefs.edit().clear().apply()
    }
}