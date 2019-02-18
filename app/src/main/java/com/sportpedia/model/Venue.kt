package com.sportpedia.model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class Venue(
    @Exclude
    val id: String = "",
    val name: String = "",
    val ownerId: String = "",
    val imgUrl: String = "",
    val adress: String = "",
    @ServerTimestamp
    val creationTime: Date? = null,
    val startingPrice: Int = 0
)