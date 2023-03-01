package com.example.webanttrainee.presentation.ui.base

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(
    private val viewBindingInflater: (inflater: LayoutInflater) -> VB
) : Fragment() {

    protected lateinit var viewModel: VM
    protected abstract fun getViewModelClass(): VM

    private var _binding: VB? = null
    val binding: VB
        get() = _binding as VB

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = viewBindingInflater.invoke(inflater)
        viewModel = getViewModelClass()
        return binding.root
    }

    fun onScrollListener() = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val lastVisibleItem = (recyclerView.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
            val totalItemsCount = recyclerView.adapter?.itemCount ?: 0
            if (totalItemsCount - lastVisibleItem <= 25) {
                viewModel.getImages()
            }
        }
    }
}