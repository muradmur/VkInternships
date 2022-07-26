package com.example.webanttrainee.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.webanttrainee.ItemClickListener
import com.example.webanttrainee.R
import com.example.webanttrainee.model.Data
import com.example.webanttrainee.model.PictureList
import com.example.webanttrainee.ui.viewholders.PictureViewHolder


class PictureAdapter(
    private val pictureList: PictureList,
    private val itemListener: ItemClickListener<Data>
): RecyclerView.Adapter<PictureViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.picture_preview_item, parent, false)
        return PictureViewHolder(view)
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.onBind(pictureList.result[position])

        holder.itemView.setOnClickListener {
            itemListener.onClick(pictureList.result[position])
        }
    }

    override fun getItemCount(): Int = pictureList.result.size
}