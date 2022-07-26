package com.example.webanttrainee.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.webanttrainee.R


class PictureAdapter :
    RecyclerView.Adapter<PictureAdapter.PictureViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.picture_preview_item, parent, false)
        return PictureViewHolder(view)
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class PictureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val previewPicture: ImageView = itemView.findViewById(R.id.ivPreview)

        fun onBind() {

        }
    }
}