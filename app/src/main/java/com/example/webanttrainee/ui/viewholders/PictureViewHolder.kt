package com.example.webanttrainee.ui.viewholders

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.webanttrainee.App
import com.example.webanttrainee.App.Companion.BASE_URL
import com.example.webanttrainee.databinding.PicturePreviewItemBinding
import com.example.webanttrainee.model.Data

class PictureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = PicturePreviewItemBinding.bind(itemView)

    fun onBind(data: Data) {

//        Log.d("ViewHolder", "${App.BASE_URL}/media/${data.image.name}")
        Glide.with(itemView.context)
            .load("${App.BASE_URL}/media/${data.image.name}")
            .into(binding.ivPreview)
    }
}