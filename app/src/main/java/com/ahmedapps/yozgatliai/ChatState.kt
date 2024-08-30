package com.ahmedapps.yozgatliai

import android.graphics.Bitmap
import com.ahmedapps.yozgatliai.data.Chat

/**
 * @author Ahmed Guedmioui
 */
data class ChatState (
    val chatList: MutableList<Chat> = mutableListOf(),
    val prompt: String = "",
    val bitmap: Bitmap? = null
)