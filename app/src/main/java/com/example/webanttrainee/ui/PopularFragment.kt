package com.example.webanttrainee.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webanttrainee.App
import com.example.webanttrainee.App.Companion.ARG_DATA
import com.example.webanttrainee.ItemClickListener
import com.example.webanttrainee.R
import com.example.webanttrainee.databinding.ContentFragmentBinding
import com.example.webanttrainee.model.Data
import com.example.webanttrainee.model.PictureResponse
import com.example.webanttrainee.remote.PictureService
import com.example.webanttrainee.ui.adapters.PictureAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class PopularFragment : Fragment() {

    // так быть не должно, надо это отрефакторить. мне не нравится кол-во переменных
    private lateinit var binding: ContentFragmentBinding
    private lateinit var pictureAdapter: PictureAdapter
    private lateinit var myLayoutManager: LinearLayoutManager
    private var page = 1
    private var totalPage = 1
    private var limit = 12
    private var isLoading = false
    private val api: PictureService
        get() = (activity?.application as App).pictureApi

    private val popularClickListener = object : ItemClickListener<Data> {
        override fun onClick(value: Data) {
            findNavController().navigate(
                R.id.action_popularFragment_to_descriptionPopularFragment,
                bundleOf(ARG_DATA to value)
            )
        }
    }

    override fun onStart() {
        super.onStart()
        initRecycler()
        getImages()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        myLayoutManager = GridLayoutManager(this.context, 2, GridLayoutManager.VERTICAL, false)
        binding = ContentFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.addOnScrollListener(onScrollListener())
        binding.refreshLayout.setOnRefreshListener {
            onRefresh()
        }
    }

    override fun onPause() {
        super.onPause()
        page = 1
        isLoading = false
        binding.customProgressBar.isVisible = false
    }

    private fun onScrollListener() = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val visibleItemCount = recyclerView.layoutManager?.childCount
            val pastVisibleItem = myLayoutManager.findFirstCompletelyVisibleItemPosition()
            val total = binding.recycler.adapter?.itemCount
            if (!isLoading && page < totalPage) {
                if (visibleItemCount != null) {
                    if ((visibleItemCount + pastVisibleItem) >= total!!) {
                        page++
                        getImages()
                    }
                }
            }
            super.onScrolled(recyclerView, dx, dy)
        }
    }

    private fun onRefresh() {
        page = 1
        getImages()
        binding.refreshLayout.isRefreshing = false
    }

    private fun initRecycler() {
        with(binding.recycler) {
            pictureAdapter = PictureAdapter(popularClickListener)
            adapter = pictureAdapter
            layoutManager = myLayoutManager
        }
    }

    private fun getImages() {
        isLoading = true
        binding.customProgressBar.isVisible = true
        val compositeDisposable = CompositeDisposable()
        api.let {
            api.getPicture(false, page, limit)
                .subscribeOn(Schedulers.io())
                .delay(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onResponse(it)
                }, {
                    onFailure(it)
                }).let(compositeDisposable::add)
        }
    }

    private fun onResponse(response: PictureResponse) {
//        pictureAdapter.addItems(response.result)
        isLoading = false
        totalPage = response.countOfPages
        binding.customProgressBar.isVisible = false
    }

    private fun onFailure(`throw`: Throwable?) {
        findNavController().navigate(R.id.action_popular_fragment_to_noInternetFragment3)
//        Toast.makeText(requireContext(), `throw`?.toString(), Toast.LENGTH_LONG).show()
        isLoading = false
        binding.customProgressBar.isVisible = false
        binding.refreshLayout.isRefreshing = false
    }


}