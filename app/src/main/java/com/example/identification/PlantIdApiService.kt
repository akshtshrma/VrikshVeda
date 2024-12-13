import com.example.identification.PlantIdRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface PlantIdApiService {
    @POST("identify")
    suspend fun identifyPlant(
        @Header("Api-Key") apiKey: String,
        @Body request: PlantIdRequest
    ): PlantIdResponse
}

