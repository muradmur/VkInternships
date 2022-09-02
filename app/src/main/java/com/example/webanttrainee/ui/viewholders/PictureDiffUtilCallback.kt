package com.example.webanttrainee.ui.viewholders

import androidx.recyclerview.widget.DiffUtil
import com.example.webanttrainee.model.Data

class PictureDiffUtilCallback : DiffUtil.ItemCallback<Data>() {

    override fun areItemsTheSame(oldItem: Data, newItem: Data) = oldItem === newItem

    override fun areContentsTheSame(oldItem: Data, newItem: Data) = oldItem.name == newItem.name
}