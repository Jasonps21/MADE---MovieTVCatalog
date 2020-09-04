package com.jason.movietvcatalog.`interface`

import com.jason.movietvcatalog.core.domain.model.Movie

interface OnItemClickCallback {
    fun onItemClicked(data: Movie)
}