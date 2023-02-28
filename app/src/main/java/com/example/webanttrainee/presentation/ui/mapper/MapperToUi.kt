package com.example.webanttrainee.presentation.ui.mapper

import com.example.data.model.Data
import com.example.data.model.Images
import com.example.data.model.Original


fun mapDataToUi(listDomain: List<com.example.domain.model.Data>): List<Data> {
    return listDomain.map {
        Data(
            content_url = it.content_url,
            id = it.id,
            import_datetime = it.import_datetime,
            rating = it.rating,
            slug = it.slug,
            source = it.source,
            source_post_url = it.source_post_url,
            title = it.title,
            trending_datetime = it.trending_datetime,
            url = it.url,
            username = it.username,
            images = Images(
                original = Original(
                    url = it.images.original.url,
                )
            )
        )
    }
}