package com.example.webanttrainee.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.webanttrainee.ItemClickListener
import com.example.webanttrainee.R
import com.example.webanttrainee.model.Data
import com.example.webanttrainee.ui.viewholders.PictureViewHolder


class PictureAdapter(
    private val itemListener: ItemClickListener<Data>
) : RecyclerView.Adapter<PictureViewHolder>() {

    private var list = ArrayList<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.picture_item, parent, false)
        return PictureViewHolder(view)
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.onBind(list[position])

        holder.itemView.setOnClickListener {
            itemListener.onClick(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    fun addItems(items: ArrayList<Data>) {
        list.addAll(items)
        notifyDataSetChanged()
    }
}