package com.sportpedia.ui.booking.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.sportpedia.R
import com.sportpedia.adapter.VenueAdapter
import com.sportpedia.model.Venue
import com.sportpedia.ui.booking.viewmodel.VenueViewModel
import com.sportpedia.util.Const
import kotlinx.android.synthetic.main.fragment_venue.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class VenueFragment : Fragment(), AnkoLogger {
    private lateinit var viewModel: VenueViewModel
    private val venueRef = FirebaseFirestore.getInstance().collection(Const.venue)

    private lateinit var venueAdp: VenueAdapter
    private val venues = mutableListOf<Venue>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            viewModel = ViewModelProviders.of(it).get(VenueViewModel::class.java)
        }

        venueAdp = VenueAdapter(context!!, venues) {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_venue, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvShimmer.showShimmerAdapter()
        rvContent.layoutManager = LinearLayoutManager(context!!)
        rvContent.adapter = venueAdp

        viewModel.bookedId.observe(this, Observer {
            info { "bookedId: $it" }
            getAllVenue()
        })
    }

    private fun getAllVenue() {
        venueRef.get().addOnCompleteListener {
            if (it.isSuccessful) {
                venues.clear()
                for (doc in it.result!!.documents) {
                    val venue = doc.toObject(Venue::class.java)!!
                    venue.id = doc.id
                    venues.add(venue)
                }

                venueAdp.notifyDataSetChanged()
                rvShimmer.hideShimmerAdapter()
                rvContent.visibility = View.VISIBLE
            }
        }
    }
}