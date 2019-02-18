package com.sportpedia.model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class Field(
    @Exclude
    val id: String = "",
    val ownerId: String = "",
    val venueId: String = "",
    val name: String = "",
    val imgUrl: String = "",
    val facilities: List<String>? = null,
    @ServerTimestamp
    val creationTime: Date? = null,
    val price: Int = 0
)