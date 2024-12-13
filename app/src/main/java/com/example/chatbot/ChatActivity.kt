package com.example.chatbot

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vrikshveda.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatActivity : AppCompatActivity() {

    private lateinit var apiService: GeminiChatApi
    private lateinit var messageInput: EditText
    private lateinit var sendButton: ImageButton
    private lateinit var chatResponse: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        messageInput = findViewById(R.id.messageInput)
        sendButton = findViewById(R.id.sendButton)
        chatResponse = findViewById(R.id.chatResponse)

        apiService = RetrofitInstance.retrofit.create(GeminiChatApi::class.java)

        sendButton.setOnClickListener {
            val message = messageInput.text.toString()
            if (message.isNotEmpty()) {
                sendMessageToChatBot(message)
            }
        }
    }

    private fun sendMessageToChatBot(message: String) {
        val request = ChatRequest(message = message, user_id = "your_user_id") // Replace with actual user_id if needed

        apiService.sendMessage(request).enqueue(object : Callback<ChatResponse> {
            override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    chatResponse.text = response.body()?.reply
                } else {
                    Toast.makeText(this@ChatActivity, "Failed to get response", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
                Toast.makeText(this@ChatActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
