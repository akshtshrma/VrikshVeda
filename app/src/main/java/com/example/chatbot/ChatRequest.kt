package com.example.chatbot

data class ChatRequest(
    val message: String,
    val user_id: String
)


data class ChatResponse(
    val reply: String
)
