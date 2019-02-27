package com.sportpedia.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.sportpedia.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_field_image.view.*

class ImagePagerAdapter(context: Context, private val images: List<String>) : PagerAdapter() {
    private val inflater = LayoutInflater.from(context)
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val v = inflater.inflate(R.layout.item_field_image, container, false)
        Picasso.get().load(images[position]).centerCrop().fit().into(v.imgField)
        container.addView(v)
        return v
    }

    override fun getCount(): Int = images.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val v = `object` as View
        container.removeView(v)
    }
}