package com.sportpedia.model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class Venue(
    @Exclude
    var id: String = "",
    val name: String = "",
    val ownerId: String = "",
    val imgUrl: String? = null,
    val address: String = "",
    @ServerTimestamp
    val creationTime: Date? = null,
    val startingPrice: Int = 0,
    val location: GeoPoint? = null,
    val fieldCount: List<String>? = null
)