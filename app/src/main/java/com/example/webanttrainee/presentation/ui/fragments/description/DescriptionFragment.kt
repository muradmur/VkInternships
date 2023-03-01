package com.example.webanttrainee.presentation.ui.fragments.description

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.webanttrainee.R
import com.example.webanttrainee.databinding.DescriptionFragmentBinding

class DescriptionFragment : Fragment() {

    private lateinit var binding: DescriptionFragmentBinding
    private val args: DescriptionFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DescriptionFragmentBinding.inflate(layoutInflater, container, false)
        binding.tvDescription.text = args.data.title
        binding.tvHeaderDescription.text = args.data.trending_datetime
        Glide.with(binding.root.context)
            .asGif()
            .load(args.data.images.original.url)
            .disallowHardwareConfig()
            .into(binding.ivPoster)
        return binding.root
    }
}