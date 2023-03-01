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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setupMenu()
    }

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

//    private fun setupMenu() {
//        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
//            override fun onPrepareMenu(menu: Menu) {
//                // Handle for example visibility of menu items
//            }
//
//            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
//                menuInflater.inflate(R.menu.toolbar_menu, menu)
////                val item = menu.findItem(R.menu.toolbar_menu)
////                item.isVisible = false
//            }
//
//            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
//                // Validate and handle the selected menu item
//                return false
//            }
//        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
//    }
}