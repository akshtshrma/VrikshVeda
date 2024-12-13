package com.example.identification

data class PlantIdRequest(
    val images: List<String>,
    val organs: List<String>
)

