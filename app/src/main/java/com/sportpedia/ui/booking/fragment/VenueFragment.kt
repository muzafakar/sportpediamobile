package com.sportpedia.ui.booking.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sportpedia.R
import com.sportpedia.adapter.VenueAdapter
import com.sportpedia.model.Venue
import com.sportpedia.viewmodel.VenueViewModel
import com.sportpedia.util.TimeUtil
import com.sportpedia.viewmodel.FieldViewModel
import kotlinx.android.synthetic.main.date_selector.*
import kotlinx.android.synthetic.main.fragment_venue.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class VenueFragment : Fragment(), AnkoLogger {
    private lateinit var venueViewModel: VenueViewModel
    private lateinit var fieldViewModel: FieldViewModel
    private lateinit var venueAdp: VenueAdapter

    private val venuesObserver = Observer<List<Venue>> {
        venueAdp.venues.clear()
        venueAdp.venues.addAll(it)
        rvShimmer.hideShimmerAdapter()
        rvContent.visibility = View.VISIBLE
    }

    private val categoryObserver = Observer<String> {
        info { "category: $it" }
        venueViewModel.getVenue(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            venueViewModel = ViewModelProviders.of(it).get(VenueViewModel::class.java)
            fieldViewModel = ViewModelProviders.of(it).get(FieldViewModel::class.java)
        }

        venueAdp = VenueAdapter(context!!) {
            info { "bookedId: ${TimeUtil.toBookedId(tvDate.text.toString())}" }
            venueViewModel.selectedVenue.value = it
            fieldViewModel.getField(it.id)
            findNavController().navigate(R.id.toField)
        }

        venueViewModel.category.observe(this, categoryObserver)
        venueViewModel.venues.observe(this, venuesObserver)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_venue, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvContent.layoutManager = LinearLayoutManager(context!!)
        rvContent.adapter = venueAdp

        tvDate.text = TimeUtil.today()
        tvDate.setOnClickListener {
            TimeUtil.setDate(context!!, it)
        }

        venueViewModel.venues.value?.let {
            if (it.isNotEmpty()) {
                rvShimmer.hideShimmerAdapter()
                rvContent.visibility = View.VISIBLE
            }
        }
    }
}