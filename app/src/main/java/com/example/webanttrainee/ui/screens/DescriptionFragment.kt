package com.example.webanttrainee.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.webanttrainee.databinding.DescriptionFragmentBinding
import com.example.webanttrainee.remote.PictureService
import dagger.hilt.android.AndroidEntryPoint

class DescriptionFragment : Fragment() {

    private lateinit var binding: DescriptionFragmentBinding
    private val args: DescriptionFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DescriptionFragmentBinding.inflate(layoutInflater, container, false)
        binding.tvDescription.text = args.response.description
        binding.tvHeaderDescription.text = args.response.name
        Glide.with(binding.ivPoster.context)
            .load("${PictureService.BASE_URL}/media/${args.response.image.name}")
            .into(binding.ivPoster)
        return binding.root
    }
}