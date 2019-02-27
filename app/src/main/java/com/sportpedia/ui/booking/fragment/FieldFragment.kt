package com.sportpedia.ui.booking.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.github.florent37.kotlin.pleaseanimate.please
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.sportpedia.R
import com.sportpedia.adapter.FieldAdapter
import com.sportpedia.model.Booked
import com.sportpedia.model.Field
import com.sportpedia.model.Venue
import com.sportpedia.viewmodel.FieldViewModel
import com.sportpedia.viewmodel.VenueViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_venue_field.*
import kotlinx.android.synthetic.main.layout_venue_detail.*
import org.jetbrains.anko.AnkoLogger

class FieldFragment : Fragment(), AnkoLogger {
    private lateinit var gMap: GoogleMap
    private lateinit var mapFragment: SupportMapFragment

    private lateinit var venueViewModel: VenueViewModel
    private lateinit var fieldViewModel: FieldViewModel

    private lateinit var fieldAdapter: FieldAdapter

    private val selectedVenueObserver = Observer<Venue> {
        populateDetail(it)
        populateMap(it)
    }
    private val fieldObserver = Observer<List<Field>> {
        fieldAdapter.fields.clear()
        fieldAdapter.fields.addAll(it)
        fieldAdapter.notifyDataSetChanged()
    }
    private val bookedScheduleObserver = Observer<Booked>{
        fieldAdapter.booked = it
        fieldAdapter.notifyDataSetChanged()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fieldAdapter = FieldAdapter(context!!) {
            // TODO add btnBooking listener
        }

        activity?.let {
            venueViewModel = ViewModelProviders.of(it).get(VenueViewModel::class.java)
            fieldViewModel = ViewModelProviders.of(it).get(FieldViewModel::class.java)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_venue_field, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapFragment = childFragmentManager.findFragmentById(R.id.mapVenue) as SupportMapFragment
        icVenueArrow.setOnClickListener { detailCardAnim() }
        icFieldArrow.setOnClickListener { fieldCardAnim() }

        rvField.layoutManager = LinearLayoutManager(context!!)
        rvField.adapter = fieldAdapter

        venueViewModel.selectedVenue.observe(this, selectedVenueObserver)
        fieldViewModel.fields.observe(this, fieldObserver)

        venueViewModel.bookedId.value
    }

    @SuppressLint("SetTextI18n")
    private fun populateDetail(venue: Venue) {
        //Toolbar
        venue.imgUrl?.let { url ->
            Picasso.get().load(url).fit().centerCrop().into(imgVenue)
            tbVenue.title = venue.name
        }

        //CardDetail
        tvVenueName.text = venue.name
        tvVenueAddress.text = venue.address
        tvOpenHour.text = "08:00 - 22:00 (statis)"
        tvFacilities.text = "Parkir gratis, Wifi, Musholla (statis)"
        venue.fieldCount?.let { tvFieldCount.text = "${it.size}" }
    }

    private fun populateMap(venue: Venue) {
        mapFragment.getMapAsync { map ->
            gMap = map
            venue.location?.let { point ->
                val venueLocation = LatLng(point.latitude, point.longitude)
                gMap.addMarker(MarkerOptions().position(venueLocation).title(venue.name))
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(venueLocation, 12.23f))
            }
        }
    }

    private fun detailCardAnim() {
        TransitionManager.beginDelayedTransition(venueField)
        if (layoutVenueDetail.visibility == View.GONE) {
            layoutVenueDetail.visibility = View.VISIBLE
            please(duration = 100L) { animate(icVenueArrow) { toBeRotated(180f) } }.start()
        } else {
            layoutVenueDetail.visibility = View.GONE
            please(duration = 100L) { animate(icVenueArrow) { toBeRotated(0f) } }.start()
        }
    }

    private fun fieldCardAnim() {
        TransitionManager.beginDelayedTransition(venueField)
        if (rvField.visibility == View.GONE) {
            rvField.visibility = View.VISIBLE
            please(duration = 100L) { animate(icFieldArrow) { toBeRotated(180f) } }.start()
        } else {
            rvField.visibility = View.GONE
            please(duration = 100L) { animate(icFieldArrow) { toBeRotated(0f) } }.start()
        }
    }
}