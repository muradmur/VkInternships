package com.example.webanttrainee.ui.viewholders

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.webanttrainee.App
import com.example.webanttrainee.databinding.PictureItemBinding
import com.example.webanttrainee.model.Data

class PictureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = PictureItemBinding.bind(itemView)

    fun onBind(data: Data) {

        Glide.with(itemView.context)
            .load("${App.BASE_URL}/media/${data.image.name}")
            .into(binding.ivPreview)
    }
}