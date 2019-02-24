package com.sportpedia.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sportpedia.R
import com.sportpedia.model.Category
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(
    private val context: Context,
    private val categories: List<Category>,
    private val listener: (Category) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_category, parent, false))

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(categories[position], listener)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(category: Category, listener: (Category) -> Unit) {
            itemView.imgCategory.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_badminton))
            itemView.setOnClickListener { listener(category) }
        }
    }

}