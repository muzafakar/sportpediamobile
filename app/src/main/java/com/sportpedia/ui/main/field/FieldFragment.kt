package com.sportpedia.ui.main.field

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sportpedia.R
import kotlinx.android.synthetic.main.fragment_field.*

class FieldFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_field, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvShimmer.showShimmerAdapter()
        Handler().postDelayed({
            rvShimmer.hideShimmerAdapter()
            rvField.visibility = View.VISIBLE
        }, 5000)
    }
}