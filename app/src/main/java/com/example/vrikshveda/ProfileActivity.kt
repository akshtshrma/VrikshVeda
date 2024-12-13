package com.example.vrikshveda

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vrikshveda.databinding.ActivityMainBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }
}