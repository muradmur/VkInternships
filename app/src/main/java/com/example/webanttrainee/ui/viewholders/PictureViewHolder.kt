package com.example.webanttrainee.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.webanttrainee.App
import com.example.webanttrainee.ItemClickListener
import com.example.webanttrainee.databinding.PictureItemBinding
import com.example.webanttrainee.model.Data

class PictureViewHolder(private val binding: PictureItemBinding, private val callback: ItemClickListener<Data>) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Data) {
        binding.root.setOnClickListener { callback.onClick(data) }
        Glide.with(binding.root.context)
            .load("${App.BASE_URL}/media/${data.image.name}")
            .into(binding.ivPreview)
    }
}