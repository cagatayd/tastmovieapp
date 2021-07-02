package com.cagatay.movieapp.repository

import com.cagatay.movieapp.model.BaseResponse
import com.cagatay.movieapp.service.MovieApi
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class repository @Inject constructor(
    private val movieApi: MovieApi
) {

      suspend fun searchMovie(querytring: String)=movieApi.movieSearch(querytring)
      suspend fun searchMovieDetail(querytring: String)=movieApi.movieSearchDetaii(querytring)




}