package com.sportpedia.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.sportpedia.R
import com.sportpedia.model.Booked
import com.sportpedia.model.Field
import kotlinx.android.synthetic.main.item_field.view.*

class FieldAdapter(
    private val context: Context,
    private val listener: (Field) -> Unit
) : RecyclerView.Adapter<FieldAdapter.ViewHolder>() {
    var booked: Booked? = Booked()
    val fields = mutableListOf<Field>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_field, parent, false))

    override fun getItemCount(): Int = fields.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(fields[position], listener)
        booked?.let {
            holder.bindBooked(fields[position], it)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bindItem(field: Field, listener: (Field) -> Unit) {
            itemView.tvFieldName.text = field.name
            itemView.tvFloorType.text = field.floorType
            itemView.tvFieldSize.text = field.size
            field.facilities?.let { itemView.tvFacilities.text = it.toString() }

            // TODO cek apakah yang dipilih jam malam
            itemView.btnBooking.text = "Rp. ${field.priceA}"


            field.imgUrl?.let { bindImage(it) }
        }

        fun bindBooked(field: Field, booked: Booked) {
            when (field.category) {
                "futsal" -> {
                    booked.futsal?.let { hashMap ->
                        hashMap[field.id]?.let {
                            itemView.tvBooked.text = it.toString()
                        }
                    }
                }
                "basketball" -> {
                    booked.basketball?.let { hashMap ->
                        hashMap[field.id]?.let {
                            itemView.tvBooked.text = it.toString()
                        }
                    }
                }
                "badminton" -> {
                    booked.badminton?.let { hashMap ->
                        hashMap[field.id]?.let {
                            itemView.tvBooked.text = it.toString()
                        }
                    }
                }
                "soccer" -> {
                    booked.soccer?.let { hashMap ->
                        hashMap[field.id]?.let {
                            itemView.tvBooked.text = it.toString()
                        }
                    }
                }
            }
        }

        private fun bindImage(imageUrl: List<String>) {
            val imagePagerAdapter = ImagePagerAdapter(context, imageUrl)
            itemView.vpFieldImage.adapter = imagePagerAdapter
            generateDots(imageUrl.size, 0)
            itemView.vpFieldImage.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {}

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

                override fun onPageSelected(position: Int) {
                    generateDots(imageUrl.size, position)
                }
            })
        }

        private fun generateDots(size: Int, position: Int) {
            val textViews = mutableListOf<TextView>()
            textViews.clear()
            itemView.dotContainer.removeAllViews()
            for (i in 1..size) {
                val tv = TextView(context)
                tv.text = Html.fromHtml("&#8226")
                tv.setPadding(2, 0, 2, 0)
                tv.textSize = 32f
                tv.setTextColor(ContextCompat.getColor(context, R.color.primaryLightColor))
                textViews.add(tv)
            }
            textViews.forEach {
                itemView.dotContainer.addView(it)
            }

            textViews[position].setTextColor(ContextCompat.getColor(context, R.color.primaryDarkColor))
        }
    }

}