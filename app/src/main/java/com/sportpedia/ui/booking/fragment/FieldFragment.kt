package com.sportpedia.ui.booking.fragment

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.transition.TransitionManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.sportpedia.R
import com.sportpedia.model.Venue
import com.sportpedia.ui.booking.viewmodel.VenueViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_venue_field.*
import kotlinx.android.synthetic.main.layout_venue_detail.*

class FieldFragment : Fragment() {
    private lateinit var viewModel: VenueViewModel
    private lateinit var gMap: GoogleMap
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            viewModel = ViewModelProviders.of(it).get(VenueViewModel::class.java)
        }

        val test = HashMap<String, Int>()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_venue_field, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvVenueCardDetail.setOnClickListener { collapseExpandVenueDetail() }
        tvFieldCardList.setOnClickListener { collapseExpandFieldDetail() }
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapVenue) as SupportMapFragment

        observerVenue(mapFragment)
        viewModel.bookedId.observe(this, Observer {
            getBookedData(it)
        })
    }

    private fun observerVenue(mapFragment: SupportMapFragment) {
        viewModel.venue.observe(this, Observer { venue ->
            populateDetail(venue)
            getField(venue.id)
            mapFragment.getMapAsync { map ->
                gMap = map
                venue.location?.let { point ->
                    val venueLocation = LatLng(point.latitude, point.longitude)
                    gMap.addMarker(MarkerOptions().position(venueLocation).title(venue.name))
                    gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(venueLocation, 12.23f))
                }
            }
        })
    }

    private fun populateDetail(venue: Venue) {
        //Toolbar
        venue.imgUrl?.let { url ->
            Picasso.get().load(url).fit().centerCrop().into(imgVenue)
            tbVenue.title = venue.name
            tbVenue.subtitle = venue.address
        }

        //CardDetail
        tvVenueName.text = venue.name
        tvVenueAddress.text = venue.address
        tvOpenHour.text = "08:00 - 22:00 (statis)"
        tvFacilities.text = "Parkir gratis, Wifi, Musholla (statis)"
        venue.fieldCount?.let { tvFieldCount.text = "${it.size}" }
    }

    private fun collapseExpandVenueDetail() {
        TransitionManager.beginDelayedTransition(venueField)
        if (layoutVenueDetail.visibility == View.GONE) {
            layoutVenueDetail.visibility = View.VISIBLE
            tvVenueCardDetail.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_up, 0)
        } else {
            layoutVenueDetail.visibility = View.GONE
            tvVenueCardDetail.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down, 0)
        }

    }

    private fun collapseExpandFieldDetail() {
        /*TransitionManager.beginDelayedTransition(venueField)
        if (layoutFieldContent.visibility == View.GONE) {
            layoutFieldContent.visibility = View.VISIBLE
            tvFieldCardList.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_up, 0)
        } else {
            layoutFieldContent.visibility = View.GONE
            tvFieldCardList.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down, 0)
        }

        val animation = ObjectAnimator.ofInt(layoutFieldContent, "height", layoutFieldContent.height)
        animation.duration = 200
        animation.start()*/
    }

    private fun getBookedData(bookedId: String) {
        firestore.document("booked/$bookedId").get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    // TODO save the selected day booked data to use later
                }
            }
    }

    private fun getField(venueId: String) {
        firestore.collection("field")
            .whereEqualTo("venueId", venueId)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    // TODO populate result to recyclerView
                }
            }
    }

}