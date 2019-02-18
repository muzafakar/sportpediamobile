package com.sportpedia.model

import com.google.firebase.firestore.Exclude
import java.util.*

data class Booking(
    @Exclude
    val id: String = "",
    val ownerId: String = "",
    val venueId: String = "",
    val fieldId: String = "",
    val userId: String = "",
    val orderId: String = "",
    val amount: Int = 0,
    val paidOf: Boolean = false,
    val forTime: Date? = null,
    val duration: Int = 0,
    val showInUser: Boolean = true,
    val showInOwner: Boolean = true,
    val creationTime: Date? = null
)