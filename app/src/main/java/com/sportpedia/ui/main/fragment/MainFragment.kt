package com.sportpedia.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.sportpedia.R
import com.sportpedia.ui.booking.BookingActivity
import com.sportpedia.ui.booking.viewmodel.VenueViewModel
import com.sportpedia.util.Dialogs
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivity

class MainFragment : Fragment(), AnkoLogger {
//    private lateinit var viewModel: VenueViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_main, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       /* activity?.let {
            viewModel = ViewModelProviders.of(it).get(VenueViewModel::class.java)
        }*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etDate.clearFocus()
        setupViews()
    }

    private fun setupViews() {
        etDate.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                Dialogs.setDate(context!!, v)
            }
        }

        btnSearch.setOnClickListener {
            val dateString = etDate.text.toString()
            if (dateString.isNotEmpty()) {

                val bookedId = Dialogs.toBookedId(etDate.text.toString())
                info { "bookedId: $bookedId" }
                context!!.startActivity<BookingActivity>("bookedId" to bookedId)
            }
        }
    }
}