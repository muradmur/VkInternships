package com.example.webanttrainee.ui.newScreen


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webanttrainee.App.Companion.ARG_DATA
import com.example.webanttrainee.ItemClickListener
import com.example.webanttrainee.R
import com.example.webanttrainee.databinding.ContentFragmentBinding
import com.example.webanttrainee.model.Data
import com.example.webanttrainee.remote.PictureRepository
import com.example.webanttrainee.remote.PictureService
import com.example.webanttrainee.ui.adapters.PictureAdapter

class NewFragment : Fragment() {

    private lateinit var viewModel: NewViewModel
    private lateinit var binding: ContentFragmentBinding
    private lateinit var myLayoutManager: LinearLayoutManager
    private lateinit var pictureAdapter: PictureAdapter
    private val pictureService: PictureService
        get() = PictureService.getInstance()

    private var page = 1
    private var limit = 12
    var isLoading = false

    //    private var pictureRepository = PictureRepository(pictureService, true, page, limit)
    private var pictureRepository = PictureRepository(pictureService)

    // инициализируется в getImages и нужен, чтобы при прокрутке вверх не было прогресс бара
    private var totalPage = 1

    override fun onStart() {
        super.onStart()

        viewModel.getImages(true, page, limit)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(
            this,
            NewViewModelFactory(pictureRepository)
        )[NewViewModel::class.java]

        binding = ContentFragmentBinding.inflate(layoutInflater, container, false)
        myLayoutManager = GridLayoutManager(this.context, 2, GridLayoutManager.VERTICAL, false)
        binding.recycler.addOnScrollListener(onScrollListener())
        initRecycler()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        binding.refreshLayout.setOnRefreshListener {
            onRefresh()
        }
    }

    override fun onPause() {
        super.onPause()
        pictureAdapter.clear()
        page = 1
        // подумать куда выносить стейт и нужно ли
        binding.customProgressBar.isVisible = false
    }

    private fun initRecycler() {
        with(binding.recycler) {
            pictureAdapter = PictureAdapter(newClickListener)
            adapter = pictureAdapter
            layoutManager = myLayoutManager
        }
    }

    private fun onRefresh() {
        pictureAdapter.clear()
        page = 1

        viewModel.getImages(true, page, limit)

        binding.refreshLayout.isRefreshing = false
    }

    //Todo Вынести в абстракцию, поправить пролистывание списка
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
                        viewModel.getImages(true, page, limit)
                    }
                }
            }

            super.onScrolled(recyclerView, dx, dy)
        }
    }

    private val newClickListener: ItemClickListener<Data>
        get() = object : ItemClickListener<Data> {
            override fun onClick(value: Data) {
                findNavController().navigate(
                    R.id.action_newFragment_to_descriptionNewFragment,
                    bundleOf(ARG_DATA to value)
                )
            }
        }

    private fun observeViewModel() {

        viewModel.pictureList.observe(viewLifecycleOwner) {
            pictureAdapter.addItems(it as ArrayList<Data>)
        }

        viewModel.isVisible.observe(viewLifecycleOwner) {
            binding.customProgressBar.isVisible = it
        }

        viewModel.isRefreshing.observe(viewLifecycleOwner) {
            binding.refreshLayout.isRefreshing = it
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            isLoading = it
        }

        viewModel.totalPage.observe(viewLifecycleOwner) {
            totalPage = it
        }

        viewModel.isError.observe(viewLifecycleOwner) {
            binding.placeHolderContainer.isVisible = it
            binding.recycler.isVisible = !it
        }
    }

    //    private fun getImages() {
//        isLoading = true
//        binding.customProgressBar.isVisible = true
//        val compositeDisposable = CompositeDisposable()
//        pictureService.let {
//            pictureService.getPicture(true, page, limit)
//                .subscribeOn(Schedulers.io())
//                .delay(3, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    onResponse(it)
//                }, {
//                    onFailure()
//                }).let(compositeDisposable::add)
//        }
//    }
//
//    private fun onResponse(response: PictureList) {
//        pictureAdapter.addItems(response.result as ArrayList<Data>)
//        totalPage = response.countOfPages
//        isLoading = false
//        binding.customProgressBar.isVisible = false
//    }
//
//    private fun onFailure() {
//        isLoading = false
//        binding.customProgressBar.isVisible = false
//        binding.refreshLayout.isRefreshing = false
////        Toast.makeText(requireContext(), _throw?.toString(), Toast.LENGTH_LONG).show()
//    }

}