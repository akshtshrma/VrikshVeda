package com.example.vrikshveda

import PlantIdApiService
import PlantSuggestion
import com.example.identification.PlantIdRequest


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream
import java.util.concurrent.TimeUnit

class ScannerActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
    }

    private var photoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)

        // Launch the camera to capture an image
        captureImage()
    }

    private fun captureImage() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val photoFile = FileUtils.createImageFile(this) // Helper function to create file
        photoUri = FileUtils.getUriForFile(this, photoFile)

        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    photoUri?.let {
                        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, it)
                        val base64Image = bitmapToBase64(bitmap)
                        identifyPlant(base64Image)
                    }
                }
            }
        } else {
            showToast("Failed to capture image. Try again.")
        }
    }

    private fun bitmapToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.NO_WRAP)
    }

    private fun identifyPlant(base64Image: String) {
        val apiUrl = "https://api.plant.id/v2/"
        val apiKey = "b1yFgvVygsqS2Jb8jL56IwRDG4a2BbkromTHWklAkQn50QFlwd"

        // Retrofit setup
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        val plantIdService = retrofit.create(PlantIdApiService::class.java)

        // API Request
        val request = PlantIdRequest(
            images = listOf(base64Image),
            organs = listOf("leaf") // Modify based on your image content
        )

        val progressBar: ProgressBar = findViewById(R.id.loadingProgressBar)
        progressBar.visibility = View.VISIBLE

        // Perform network request in a coroutine
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = plantIdService.identifyPlant(apiKey, request)
                progressBar.visibility = View.GONE
                if (response.suggestions.isNotEmpty()) {
                    showPlantDetails(response.suggestions[0])
                } else {
                    showToast("No plant identified. Try again.")
                }
            } catch (e: Exception) {
                showToast("Failed to identify plant: ${e.message}")
            }
        }
    }

    private fun showPlantDetails(plantSuggestion: PlantSuggestion) {
        val plantNameTextView: TextView = findViewById(R.id.plantNameTextView)
        val plantDetailsTextView: TextView = findViewById(R.id.plantDetailsTextView)

        val plantName = plantSuggestion.plant_name ?: "Unknown Plant"
        val scientificName = plantSuggestion.plant_details?.scientific_name ?: "N/A"
        val family = plantSuggestion.plant_details?.structured_name ?: "N/A"

        plantNameTextView.text = "🌿 Plant Name: $plantName"
        plantDetailsTextView.text = """
        🔬 Scientific Name: $scientificName
        🌱 Family: $family
    """.trimIndent()

        plantNameTextView.visibility = View.VISIBLE
        plantDetailsTextView.visibility = View.VISIBLE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
