package com.example.webanttrainee.ui.newScreen


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webanttrainee.R
import com.example.webanttrainee.databinding.ContentFragmentBinding
import com.example.webanttrainee.remote.PictureRepository
import com.example.webanttrainee.remote.PictureService
import com.example.webanttrainee.ui.adapters.PictureAdapter

class NewFragment : Fragment() {

    private lateinit var binding: ContentFragmentBinding
    private val pictureService by lazy { PictureService.getInstance(requireContext()) }
    private val pictureRepository by lazy { PictureRepository(pictureService) }
    private val pictureAdapter by lazy {
        PictureAdapter { findNavController().navigate(R.id.action_newFragment_to_descriptionNewFragment) }
    }
    private val viewModel: NewViewModel by lazy {
        ViewModelProvider(this, NewViewModelFactory(pictureRepository))[NewViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        ContentFragmentBinding.inflate(layoutInflater).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setupListeners()
        initRecycler()
    }

    private fun initRecycler() = with(binding) {
        recycler.adapter = pictureAdapter
    }

    private fun onScrollListener() = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val lastVisibleItem = (recyclerView.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
            val totalItemsCount = recyclerView.adapter?.itemCount ?: 0
            if (totalItemsCount - lastVisibleItem <= 4) {
                viewModel.getImages(true)
            }
        }
    }


    private fun observeViewModel() {
        viewModel.pictureList.observe(viewLifecycleOwner) {
            pictureAdapter.submitList(it)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.customProgressBar.isVisible = it
        }

        viewModel.isRefreshing.observe(viewLifecycleOwner) {
            binding.refreshLayout.isRefreshing = it
        }

        viewModel.isError.observe(viewLifecycleOwner) {
            binding.placeHolderContainer.isVisible = it
            binding.recycler.isVisible = !it
        }
    }

    private fun setupListeners() {
        binding.refreshLayout.setOnRefreshListener { viewModel.refresh() }
        binding.recycler.addOnScrollListener(onScrollListener())
    }
}