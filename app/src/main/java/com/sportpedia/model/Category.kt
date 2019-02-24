package com.sportpedia.model

import com.google.firebase.firestore.Exclude

data class Category(
    @Exclude
    var id: String = "",
    val category: String = "",
    val icon: Int? = null
)