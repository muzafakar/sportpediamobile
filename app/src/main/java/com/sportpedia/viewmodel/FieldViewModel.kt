package com.sportpedia.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.sportpedia.model.Booked
import com.sportpedia.model.Field

class FieldViewModel : ViewModel() {
    val fields: MutableLiveData<List<Field>> by lazy { MutableLiveData<List<Field>>() }
    private val fieldList = mutableListOf<Field>()
    private val firestore = FirebaseFirestore.getInstance()
    fun getField(venueId: String) {
        firestore.collection("field").whereEqualTo("venueId", venueId).get().addOnCompleteListener {
            if (it.isSuccessful) {
                fieldList.clear()
                for (doc in it.result!!.documents) {
                    val field = doc.toObject(Field::class.java)!!
                    field.id = doc.id
                    fieldList.add(field)
                }

                fields.postValue(fieldList)
            }
        }
    }

    val bookedSchedule: MutableLiveData<Booked> by lazy { MutableLiveData<Booked>() }
    fun getBookedSchedule(bookedId: String) {
        firestore.collection("booked").document(bookedId).get().addOnCompleteListener {
            if (it.isSuccessful) {
                it.result?.let { doc ->
                    if (doc.exists()) {
                        val booked = doc.toObject(Booked::class.java)
                        bookedSchedule.postValue(booked)
                    }else{
                        bookedSchedule.postValue(null)
                    }
                }
            }
        }
    }
}