package com.example.vrikshveda

import com.example.chatbot.ChatActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.quiz.MainActivityQuiz

class EducationFragment : Fragment(R.layout.fragment_education){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnOption1 = view.findViewById<Button>(R.id.btnOption1)
        val btnOption2 = view.findViewById<Button>(R.id.btnOption2)

        btnOption1.setOnClickListener {
            val intent = Intent(activity, MainActivityQuiz::class.java)
            startActivity(intent)
        }

        btnOption2.setOnClickListener {
            val intent = Intent(activity, ChatActivity::class.java)
            startActivity(intent)
        }
    }
}
