package com.example.webanttrainee.presentation.ui.fragments.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.webanttrainee.databinding.DescriptionFragmentBinding
import com.example.data.remote.Api

class DescriptionFragment : Fragment() {

    private lateinit var binding: DescriptionFragmentBinding
    private val args: DescriptionFragmentArgs by navArgs()

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//
//        binding = DescriptionFragmentBinding.inflate(layoutInflater, container, false)
//        binding.tvDescription.text = args.data.description
//        binding.tvHeaderDescription.text = args.data.name
//        Glide.with(binding.ivPoster.context)
//            .load("${Api.BASE_URL}/media/${args.data.image.name}")
//            .into(binding.ivPoster)
//        return binding.root
//    }
}