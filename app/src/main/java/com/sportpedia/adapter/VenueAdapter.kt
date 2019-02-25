package com.sportpedia.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sportpedia.R
import com.sportpedia.model.Venue
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_venue.view.*

class VenueAdapter(
    private val context: Context,
    private val venues: List<Venue>,
    private val listener: (Venue) -> Unit
) : RecyclerView.Adapter<VenueAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_venue, parent, false))

    override fun getItemCount(): Int = venues.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(venues[position], listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bindItem(venue: Venue, listener: (Venue) -> Unit) {
            itemView.tvVenueName.text = venue.name
            itemView.tvVenueAddress.text = venue.address
            itemView.tvStartingPrice.text = "Rp. ${venue.startingPrice}"

            venue.imgUrl?.let {
                Picasso.get().load(it).fit().centerCrop().into(itemView.imgVenue)
            }

            venue.fieldCount?.let {
                itemView.tvFieldCount.text = "${it.size} lapangan"
            }

            itemView.setOnClickListener { listener(venue) }
        }
    }

}