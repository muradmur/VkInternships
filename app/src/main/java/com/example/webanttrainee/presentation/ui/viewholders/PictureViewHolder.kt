package com.example.webanttrainee.presentation.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.model.Data
import com.example.webanttrainee.presentation.utils.ItemClickListener
import com.example.webanttrainee.databinding.PictureItemBinding

class PictureViewHolder(private val binding: PictureItemBinding, private val callback: ItemClickListener<Data>) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Data) {
        binding.root.setOnClickListener { callback.onClick(data) }
        Glide.with(binding.root.context)
            .asGif()
            .load(data.images.original.url)
            .disallowHardwareConfig()
            .into(binding.ivPreview)
    }
}