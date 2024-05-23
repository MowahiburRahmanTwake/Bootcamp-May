package com.example.bootcampmay.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val userId: String?,
    val userName: String?,
    val userImage: String?,
    val lastMessage: String?,
): Parcelable
