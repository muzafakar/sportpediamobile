package com.sportpedia.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.sportpedia.model.Venue
import com.sportpedia.util.Const
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class VenueViewModel : ViewModel(), AnkoLogger {
    val bookedId: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val category: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val selectedVenue: MutableLiveData<Venue> by lazy { MutableLiveData<Venue>() }

    private val venueList = mutableListOf<Venue>()
    private val venueRef = FirebaseFirestore.getInstance().collection("venue")
    val venues: MutableLiveData<List<Venue>> by lazy { MutableLiveData<List<Venue>>() }
    fun getVenue(category: String) {
        venueRef.whereEqualTo(Const.category, category).get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    venueList.clear()
                    for (doc in it.result!!.documents) {
                        val venue = doc.toObject(Venue::class.java)!!
                        venue.id = doc.id
                        venueList.add(venue)
                    }

                    info{"doc size: ${it.result!!.size()}"}
                    venues.postValue(venueList)
                }
            }
    }
}