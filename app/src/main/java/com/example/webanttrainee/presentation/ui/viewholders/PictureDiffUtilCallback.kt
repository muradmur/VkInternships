package com.example.webanttrainee.presentation.ui.viewholders

import androidx.recyclerview.widget.DiffUtil
import com.example.webanttrainee.domain.model.Data

class PictureDiffUtilCallback : DiffUtil.ItemCallback<Data>() {

    override fun areItemsTheSame(oldItem: Data, newItem: Data) = oldItem === newItem

    override fun areContentsTheSame(oldItem: Data, newItem: Data) = oldItem.name == newItem.name
}