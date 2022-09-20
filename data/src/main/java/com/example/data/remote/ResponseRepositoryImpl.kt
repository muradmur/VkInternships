package com.example.data.remote

import com.example.webanttrainee.domain.repository.ResponseRepository
import javax.inject.Inject

class ResponseRepositoryImpl @Inject constructor(
    private val api: Api,
) : ResponseRepository {

    override fun getPicture(isNew: Boolean, page: Int, limit: Int) = api.getPicture(isNew, page, limit)
}