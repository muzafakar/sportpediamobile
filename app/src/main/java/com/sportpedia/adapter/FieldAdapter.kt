package com.sportpedia.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sportpedia.R
import com.sportpedia.model.Field
import kotlinx.android.synthetic.main.item_field.view.*

class FieldAdapter(private val context: Context, private val fields: List<Field>, private val listener: (Field) -> Unit) : RecyclerView.Adapter<FieldAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_field, parent, false))

    override fun getItemCount(): Int = fields.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(fields[position], listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bindItem(field: Field, listener: (Field) -> Unit) {
            itemView.tvFieldName.text = field.name
        }
    }

}