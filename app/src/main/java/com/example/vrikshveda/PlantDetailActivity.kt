package com.example.vrikshveda

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PlantDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_detail)

        val plantName = intent.getStringExtra("PLANT_NAME")
        findViewById<TextView>(R.id.plantNameTextView).text = plantName

    }
}
