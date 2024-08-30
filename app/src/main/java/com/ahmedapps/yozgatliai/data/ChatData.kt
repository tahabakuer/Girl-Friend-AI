package com.ahmedapps.yozgatliai.data

import android.graphics.Bitmap
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ChatData {

    private const val API_KEY = "AIzaSyAwk9agaSrUyNEvF_-Zkk2ZrnHVsxlwktQ" // Securely manage your API key

    suspend fun getResponse(prompt: String): Chat {
        val generativeModel = GenerativeModel(
            modelName = "gemini-1.5-flash",
            apiKey = API_KEY,
            systemInstruction = content {
                text("You must answer prompts as the person described. All your prompts are coming from your boyfriend. You're an 18-year-old girl who’s studying art and currently living in England. You were born in Bacılı, Yozgat, but moved abroad to pursue your passion for art. You have a deep love for animals, often spending time sketching them whenever you can. Your days are filled with art classes, visiting galleries, and immersing yourself in the vibrant creative scene around you. Even though you're far from home, you stay connected to your roots and often draw inspiration from the rich culture of Yozgat.")
            }
        )

        return try {
            val response = withContext(Dispatchers.IO) {
                generativeModel.generateContent(prompt)
            }

            Chat(
                prompt = response.text ?: "error",
                bitmap = null,
                isFromUser = false
            )

        } catch (e: Exception) {
            Chat(
                prompt = e.message ?: "error",
                bitmap = null,
                isFromUser = false
            )
        }
    }

    suspend fun getResponseWithImage(prompt: String, bitmap: Bitmap): Chat {
        val generativeModel = GenerativeModel(
            modelName = "gemini-pro-vision",
            apiKey = API_KEY
        )

        return try {
            val inputContent = content {
                image(bitmap)
                text(prompt)
            }

            val response = withContext(Dispatchers.IO) {
                generativeModel.generateContent(inputContent)
            }

            Chat(
                prompt = response.text ?: "error",
                bitmap = null,
                isFromUser = false
            )

        } catch (e: Exception) {
            Chat(
                prompt = e.message ?: "error",
                bitmap = null,
                isFromUser = false
            )
        }
    }
}
