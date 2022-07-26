package com.example.webanttrainee.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.webanttrainee.App
import com.example.webanttrainee.App.Companion.ARG_DATA
import com.example.webanttrainee.App.Companion.MEDIA_URL
import com.example.webanttrainee.R
import com.example.webanttrainee.databinding.PictureDescriptionBinding
import com.example.webanttrainee.model.Data


class DescriptionNewFragment : Fragment() {

    private lateinit var binding: PictureDescriptionBinding
    private val data
        get() = requireArguments().getSerializable(ARG_DATA) as Data

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PictureDescriptionBinding.inflate(layoutInflater, container, false)

        binding.tvDescription.text = data.description
        binding.tvHeaderDescription.text = data.name
        Glide.with(binding.ivPoster.context)
            .load("${App.BASE_URL}/media/${data.image.name}")
            .into(binding.ivPoster)
        return binding.root
    }


}