package com.example.webanttrainee.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import com.example.webanttrainee.model.PictureList
import com.example.webanttrainee.remote.PictureApi
import com.example.webanttrainee.ui.adapters.PictureAdapter
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class NewFragment : Fragment() {

    private lateinit var binding: ContentFragmentBinding
    private lateinit var myLayoutManager: LinearLayoutManager
    private lateinit var pictureAdapter: PictureAdapter

    private val api: PictureApi
        get() = (activity?.application as App).pictureApi

    private val newClickListener = object : ItemClickListener<Data> {
        override fun onClick(value: Data) {
            findNavController().navigate(
                R.id.action_newFragment_to_descriptionNewFragment,
                bundleOf(ARG_DATA to value)
            )
        }
    }

    private var page = 1
    private var limit = 12
    var isLoading = false

    // инициализируется в getImages и нужна, чтобы при прокрутке вверх не было прогресс бара
    private var totalPage = 1

    override fun onStart() {
        super.onStart()

        getImages(false)
        initRecycler()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ContentFragmentBinding.inflate(layoutInflater, container, false)

        myLayoutManager = GridLayoutManager(this.context, 2, GridLayoutManager.VERTICAL, false)

        binding.recycler.addOnScrollListener(onScrollListener())
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.refreshLayout.setOnRefreshListener {
            onRefresh()
        }
    }

    override fun onPause() {
        super.onPause()
        pictureAdapter.clear()
        page = 1
        isLoading = false
        binding.progressbar.isVisible = false
    }

    private fun initRecycler() {
        with(binding.recycler) {
            pictureAdapter = PictureAdapter(newClickListener)
            adapter = pictureAdapter.apply { notifyDataSetChanged() }
            layoutManager = myLayoutManager
        }
    }

    private fun onRefresh() {
        pictureAdapter.clear()
        page = 1
        getImages(true)
        binding.refreshLayout.isRefreshing = false
    }

    private fun getImages(isRefreshing: Boolean) {
        isLoading = true
        if (!isRefreshing) binding.progressbar.isVisible = true

        Completable.timer(3, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .subscribe {
                val compositeDisposable = CompositeDisposable()
                api.let {
                    api.getPicture(true, page, limit)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            onResponse(it)
                        }, {
                            onFailure(it)
                        }).let(compositeDisposable::add)
                }
            }
    }

    private fun onResponse(response: PictureList) {
        pictureAdapter.addItems(response.result as ArrayList<Data>)
        totalPage = response.countOfPages
        isLoading = false
        binding.progressbar.isVisible = false
    }

    private fun onFailure(_throw: Throwable?) {
        isLoading = false
        binding.progressbar.isVisible = false
        binding.refreshLayout.isRefreshing = false
        Toast.makeText(requireContext(), _throw?.toString(), Toast.LENGTH_LONG).show()
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
                        getImages(false)
                    }
                }
            }
            super.onScrolled(recyclerView, dx, dy)
        }
    }
}