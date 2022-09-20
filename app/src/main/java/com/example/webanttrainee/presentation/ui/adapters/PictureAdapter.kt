package com.example.webanttrainee.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.webanttrainee.presentation.utils.ItemClickListener
import com.example.webanttrainee.databinding.PictureItemBinding
import com.example.domain.model.Data
import com.example.webanttrainee.presentation.ui.viewholders.PictureDiffUtilCallback
import com.example.webanttrainee.presentation.ui.viewholders.PictureViewHolder

class PictureAdapter(private val callback: ItemClickListener<Data>) :
    ListAdapter<Data, PictureViewHolder>(PictureDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PictureViewHolder(
        PictureItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), callback
    )

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) =
        holder.bind(currentList[position])
}