package com.example.webanttrainee.ui


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.webanttrainee.App
import com.example.webanttrainee.R
import com.example.webanttrainee.model.PictureList
import com.example.webanttrainee.remote.PictureApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class NewFragment : Fragment(R.layout.content_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchPictureList((activity?.application as App).pictureApi, true)
        fetchPictureList((activity?.application as App).pictureApi, false)
    }

    private fun fetchPictureList(newApi: PictureApi?, pictureStatus: Boolean) {

        val compositeDisposable = CompositeDisposable()
        newApi?.let {
            newApi.getPicture(pictureStatus)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onResponse(it)
                }, {
                    onFailure(it)
                }).let(compositeDisposable::add)
        }
    }

    private fun onResponse(list: PictureList) {
//        binding.recycler.adapter = UserAdapter(listUser).apply {
//            notifyDataSetChanged()
//        }
        Log.d("onResponse", list.data.size.toString())
    }

    private fun onFailure(`throw`: Throwable?){
        Toast.makeText(requireContext(), `throw`?.toString(), Toast.LENGTH_LONG).show()
    }
}