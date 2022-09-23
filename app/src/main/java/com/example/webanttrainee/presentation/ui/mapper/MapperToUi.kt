package com.example.webanttrainee.presentation.ui.mapper

import com.example.data.model.Data
import com.example.data.model.Image

fun mapDataToUi(listDomain: List<com.example.domain.model.Data>): List<Data> {
    return listDomain.map {
        Data(
            dateCreate = it.dateCreate,
            description = it.description,
            id = it.id,
            image = Image(
                id = it.image.id,
                name = it.image.name,
            ),
            name = it.name,
            new = it.new,
            popular = it.popular,
            user = if (it.user != null) it.user else ""
        )
    }
}