package fr.enssat.sharemybook.BastienLucasZakaria.data.remote

import fr.enssat.sharemybook.BastienLucasZakaria.data.model.OpenLibraryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.net.HttpURLConnection
import java.net.URL

class OpenLibraryService {

    suspend fun fetchBookByIsbn(isbn: String): OpenLibraryResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val url = URL("https://openlibrary.org/api/volumes/brief/isbn/$isbn.json")
                val connection = url.openConnection() as HttpURLConnection

                connection.requestMethod = "GET"
                connection.connectTimeout = 10_000
                connection.readTimeout = 10_000

                if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                    val json = connection.inputStream
                        .bufferedReader()
                        .use { it.readText() }

                    Json { ignoreUnknownKeys = true }
                        .decodeFromString(OpenLibraryResponse.serializer(), json)
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
    }
}
