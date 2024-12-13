package com.example.chatbot

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GeminiChatApi {
    @Headers("Content-Type: application/json")
    @POST("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent") // Update with the actual endpoint path
    fun sendMessage(@Body message: ChatRequest): Call<ChatResponse>
}