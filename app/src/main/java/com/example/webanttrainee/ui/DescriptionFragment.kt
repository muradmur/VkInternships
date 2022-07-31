package com.example.webanttrainee.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.webanttrainee.App
import com.example.webanttrainee.App.Companion.ARG_DATA
import com.example.webanttrainee.databinding.DescriptionFragmentBinding
import com.example.webanttrainee.model.Data


class DescriptionFragment : Fragment() {

    private lateinit var binding: DescriptionFragmentBinding
    private val data
        get() = requireArguments().getSerializable(ARG_DATA) as Data


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DescriptionFragmentBinding.inflate(layoutInflater, container, false)

        binding.tvDescription.text = data.description
        binding.tvHeaderDescription.text = data.name
        Glide.with(binding.ivPoster.context)
            .load("${App.BASE_URL}/media/${data.image.name}")
            .into(binding.ivPoster)
        return binding.root
    }
}