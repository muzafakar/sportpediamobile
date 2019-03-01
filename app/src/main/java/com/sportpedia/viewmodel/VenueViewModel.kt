package com.sportpedia.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.sportpedia.model.Booked
import com.sportpedia.model.Venue
import com.sportpedia.util.Const
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class VenueViewModel : ViewModel(), AnkoLogger {
    private val firestore = FirebaseFirestore.getInstance()
    val category: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val selectedVenue: MutableLiveData<Venue> by lazy { MutableLiveData<Venue>() }
    private var scheduleListener: ListenerRegistration? = null

    val bookedSchedule: MutableLiveData<Booked> by lazy { MutableLiveData<Booked>() }
    fun getBookedSchedule(bookedId: String) {
        val query = firestore.collection("booked").document(bookedId)

        removeScheduleListener()
        scheduleListener = query.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                info { exception.localizedMessage }
                return@addSnapshotListener
            }

            info { "bookedId firebase: ${snapshot?.id}" }
            val booked = snapshot?.toObject(Booked::class.java)
            bookedSchedule.postValue(booked)
        }
    }

    fun removeScheduleListener() {
        scheduleListener?.remove()
    }

    private val venueList = mutableListOf<Venue>()
    val venues: MutableLiveData<List<Venue>> by lazy { MutableLiveData<List<Venue>>() }
    fun getVenue(category: String) {
        firestore.collection("venue").whereEqualTo(Const.category, category).get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    venueList.clear()
                    for (doc in it.result!!.documents) {
                        val venue = doc.toObject(Venue::class.java)!!
                        venue.id = doc.id
                        venueList.add(venue)
                    }

                    info { "doc size: ${it.result!!.size()}" }
                    venues.postValue(venueList)
                }
            }
    }
}