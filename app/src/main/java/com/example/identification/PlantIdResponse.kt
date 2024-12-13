// The response object from the API
data class PlantIdResponse(
    val suggestions: List<PlantSuggestion>
)

// Each suggestion from the API response
data class PlantSuggestion(
    val plant_name: String, // Common name of the plant
    val probability: Double, // The probability that the identification is correct
    val confirmed: Boolean, // Whether the plant is confirmed
    val plant_details: PlantDetails // Detailed information about the plant
)

// Detailed plant information
data class PlantDetails(
    val language: String, // Language of the name
    val scientific_name: String, // Scientific name of the plant
    val structured_name: StructuredName // Structured name with genus and species

)

// Genus and species information
data class StructuredName(
    val genus: String, // Genus of the plant
    val species: String // Species of the plant
)
