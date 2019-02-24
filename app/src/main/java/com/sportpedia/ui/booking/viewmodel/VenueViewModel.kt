package com.sportpedia.ui.booking.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class VenueViewModel : ViewModel() {
    val date: MutableLiveData<Date> by lazy { MutableLiveData<Date>() }
    val duration: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val bookedId: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val category: MutableLiveData<String> by lazy { MutableLiveData<String>() }
}