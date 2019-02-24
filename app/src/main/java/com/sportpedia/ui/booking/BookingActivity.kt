package com.sportpedia.ui.booking

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.sportpedia.R
import com.sportpedia.ui.booking.viewmodel.VenueViewModel
import com.sportpedia.util.Const
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class BookingActivity : AppCompatActivity(), AnkoLogger {
    private lateinit var viewModel: VenueViewModel
    private lateinit var category: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
        viewModel = ViewModelProviders.of(this).get(VenueViewModel::class.java)

        /*  val bookedId = intent.getStringExtra("bookedId")
          info { "bookedId: $bookedId" }
          viewModel.bookedId.value = bookedId*/

        category = intent.getStringExtra(Const.category)
        info { "category: $category" }
        viewModel.category.value = category
    }
}