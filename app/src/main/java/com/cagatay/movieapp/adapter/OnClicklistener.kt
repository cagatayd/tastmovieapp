package com.cagatay.movieapp.adapter

import android.view.View
import com.cagatay.movieapp.model.Search

interface OnClicklistener {

    fun onCountryClicked(v: View, movies: Search)
}