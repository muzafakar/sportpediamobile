package com.sportpedia.model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class User(
    @Exclude
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val address: String = "",
    val imgUrl: String = "",
    @ServerTimestamp
    val creationTime: Date? = null
)