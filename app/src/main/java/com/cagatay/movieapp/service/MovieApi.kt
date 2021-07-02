package com.cagatay.movieapp.service

import com.cagatay.movieapp.model.BaseResponse
import com.cagatay.movieapp.model.Movies
import com.cagatay.movieapp.util.util.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    // https://www.omdbapi.com/
    // ?t=spiderman&apikey=812772ef

companion object{
}
    @GET("/")
    suspend fun movieSearch(
        @Query("s")searcquery:String,
        @Query("apikey")apikey:String=API_KEY):Response<Movies>


    @GET("/")
    suspend fun movieSearchDetaii(
        @Query("t")searcquery:String,
        @Query("apikey")apikey:String=API_KEY):Response<BaseResponse>


}