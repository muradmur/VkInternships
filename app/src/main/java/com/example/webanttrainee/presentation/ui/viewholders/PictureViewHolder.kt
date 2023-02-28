package com.example.webanttrainee.presentation.ui.viewholders

import android.net.Uri
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.model.Data
import com.example.webanttrainee.presentation.utils.ItemClickListener
import com.example.webanttrainee.databinding.PictureItemBinding

class PictureViewHolder(private val binding: PictureItemBinding, private val callback: ItemClickListener<Data>) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Data) {
        binding.root.setOnClickListener { callback.onClick(data) }
        Log.d("url", data.url)
        val testUrl = "https://giphy.com/gifs/ProBitExchange-wow-nice-good-A38YkEn7xhCehkX2sV   "
        Glide.with(binding.root.context)
            .asGif()
            .load(Uri.parse(data.url.trim()))
            .disallowHardwareConfig()
            .into(binding.ivPreview)
    }
}