package com.sportpedia.ui.booking.fragment

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.TransitionManager
import com.github.florent37.kotlin.pleaseanimate.please
import com.sportpedia.R
import kotlinx.android.synthetic.main.fragment_venue_field.*

class FieldFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_venue_field, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvVenueCardDetail.setOnClickListener { collapseExpandVenueDetail() }
        tvFieldCardList.setOnClickListener { collapseExpandFieldDetail() }

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
        TransitionManager.beginDelayedTransition(venueField)
        if (layoutFieldContent.visibility == View.GONE) {
            layoutFieldContent.visibility = View.VISIBLE
            tvFieldCardList.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_up, 0)
        } else {
            layoutFieldContent.visibility = View.GONE
            tvFieldCardList.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down, 0)
        }

        val animation = ObjectAnimator.ofInt(layoutFieldContent, "height", layoutFieldContent.height)
        animation.duration = 200
        animation.start()

    }
}