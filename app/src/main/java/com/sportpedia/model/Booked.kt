package com.sportpedia.model

import com.google.firebase.firestore.Exclude

data class Booked(
    @Exclude
    var id: String = "",
    var futsal: HashMap<String, List<Int>>? = null,
    var basketball: HashMap<String, List<Int>>? = null,
    var badminton: HashMap<String, List<Int>>? = null,
    var soccer: HashMap<String, List<Int>>? = null
)