package com.example.bootcampmay.model

import java.sql.Timestamp
import java.util.Date
import java.util.Locale

data class ConversationModel(
    val userId: String,
    val userName: String,
    val userImage: String,
    val message: String,
    val imageMessage: String,
    val timestamp: Date? =null,
    val timestampLocal: String? = null

)
