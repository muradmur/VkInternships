package com.example.webanttrainee.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webanttrainee.databinding.ContentFragmentBinding
import com.example.webanttrainee.remote.PictureRepository
import com.example.webanttrainee.remote.PictureApi
import com.example.webanttrainee.ui.adapters.PictureAdapter
import com.example.webanttrainee.ui.viewModels.ViewModel
import dagger.hilt.android.AndroidEntryPoint

//import com.example.webanttrainee.ui.viewModels.ViewModelFactory

@AndroidEntryPoint
class PopularFragment : Fragment() {

    private lateinit var binding: ContentFragmentBinding
    private val pictureAdapter by lazy {
        PictureAdapter {
            findNavController().navigate(PopularFragmentDirections.actionPopularFragmentToDescriptionPopularFragment(it))
        }
    }
    private val viewModel by viewModels<ViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ContentFragmentBinding.inflate(layoutInflater).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getImages(false)
        observeViewModel()
        setupListeners()
        initRecycler()
    }

    private fun onScrollListener() = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val lastVisibleItem =
                ((recyclerView.layoutManager) as GridLayoutManager).findLastCompletelyVisibleItemPosition()
            val totalItemsCount = recyclerView.adapter?.itemCount ?: 0
            if (totalItemsCount - lastVisibleItem <= 20) {
                viewModel.getImages(false)
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
        binding.refreshLayout.setOnRefreshListener { viewModel.refresh(false) }
        binding.recycler.addOnScrollListener(onScrollListener())
    }

    private fun initRecycler() {
        with(binding.recycler) {
            adapter = pictureAdapter
        }
    }
}