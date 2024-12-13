package com.example.quiz

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.vrikshveda.databinding.ActivityMainQuizBinding


class MainActivityQuiz : AppCompatActivity() {
    private lateinit var binding: ActivityMainQuizBinding

    private val questions = arrayOf(
        "Which plant is widely known for its medicinal properties and is commonly found in Indian households?",
        "Which of the following plants is an important ingredient in Indian cuisine and is also known as 'Holy Basil'?",
        "Which plant is known as the 'Indian gooseberry' and is famous for its high vitamin C content?",
        "Which of the following spices is obtained from the dried stigmas of a flower and is commonly grown in Kashmir, India?",
        "Which plant is commonly referred to as the 'living fossil' and has been around for more than 200 million years?",
        "Which plant is famous for its colorful, trumpet-shaped flowers and is often used as an ornamental plant?",
        "Which plant is known for its heart-shaped leaves and is a symbol of love in various cultures?",
        "Which plant is well-known for fixing nitrogen in the soil through a symbiotic relationship with bacteria?",
        "Which plant produces a yellow spice commonly used in cooking and is known for its anti-inflammatory properties?",
        "Which herbal plant is commonly used to treat burns and skin irritation?"

    )
    private val options = arrayOf(
        arrayOf("Aloe Vera", "Rose", "Tulip", "Dafodils"),
        arrayOf("Correander", "Tulsi", "Mint", "Saffron"),
        arrayOf("Jamun", "Amla", "Bael", "Guava"),
        arrayOf("Cardamom", "Clove", "Saffron", "Cinnamon"),
        arrayOf("Ginkgo", "Fern", "Pine", "Bamboo"),
        arrayOf("Hibiscus", "Cactus", "Bamboo", "Pine"),
        arrayOf("Rose", "Iby", "Tulip", "Lily"),
        arrayOf("Oak", "Soyabean", "Pine", "Mango"),
        arrayOf("saffron", "Turmeric", "Ginger", "Iby"),
        arrayOf("Aloe Vera", "Basil", "Tulip", "Dafodils")
    )
    private val correctAnswers = arrayOf(0, 1, 1, 2, 0, 0, 1, 1, 1, 0)

    private var score = 0
    private var currentQuestionIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestions()

        binding.btnOption1.setOnClickListener { checkAnswer(0) }
        binding.btnOption2.setOnClickListener { checkAnswer(1) }
        binding.btnOption3.setOnClickListener { checkAnswer(2) }
        binding.btnOption4.setOnClickListener { checkAnswer(3) }
    }

    private fun correctButtonColor(buttonIndex: Int) {
        when (buttonIndex) {
            0 -> binding.btnOption1.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_green_light))
            1 -> binding.btnOption2.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_green_light))
            2 -> binding.btnOption3.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_green_light))
            3 -> binding.btnOption4.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_green_light))
        }
    }

    private fun wrongButtonColor(buttonIndex: Int) {
        when (buttonIndex) {
            0 -> binding.btnOption1.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_red_light))
            1 -> binding.btnOption2.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_red_light))
            2 -> binding.btnOption3.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_red_light))
            3 -> binding.btnOption4.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_red_light))
        }
    }

    private fun resetButtonColors() {
        val defaultColor = Color.rgb(0, 177, 64)
        binding.btnOption1.setBackgroundColor(defaultColor)
        binding.btnOption2.setBackgroundColor(defaultColor)
        binding.btnOption3.setBackgroundColor(defaultColor)
        binding.btnOption4.setBackgroundColor(defaultColor)
    }

    private fun showResults() {
        Toast.makeText(this, "Your score is $score out of ${questions.size}", Toast.LENGTH_LONG).show()
    }

    private fun displayQuestions() {
        binding.tvQuestion.text = questions[currentQuestionIndex]
        binding.btnOption1.text = options[currentQuestionIndex][0]
        binding.btnOption2.text = options[currentQuestionIndex][1]
        binding.btnOption3.text = options[currentQuestionIndex][2]
        binding.btnOption4.text = options[currentQuestionIndex][3]
        resetButtonColors()
    }

    private fun checkAnswer(selectedButtonIndex: Int) {
        val correctAnswerIndex = correctAnswers[currentQuestionIndex]

        if (selectedButtonIndex == correctAnswerIndex) {
            score++
            correctButtonColor(selectedButtonIndex)
        } else {
            wrongButtonColor(selectedButtonIndex)
            correctButtonColor(correctAnswerIndex)
        }

        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            binding.tvQuestion.postDelayed({ displayQuestions() }, 1000)
        } else {
            showResults()
        }
    }
}