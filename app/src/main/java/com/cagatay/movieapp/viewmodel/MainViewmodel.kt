package com.cagatay.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cagatay.movieapp.model.BaseResponse
import com.cagatay.movieapp.model.Movies
import com.cagatay.movieapp.repository.repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewmodel @Inject constructor(private val repository: repository)  :ViewModel() {


    val movies = MutableLiveData<Movies>()
    val moviesyerorr= MutableLiveData<Boolean>()
    val moviesLoading= MutableLiveData<Boolean>()


    fun searchMovie(searchString: String){
        if (searchString.isNotEmpty()){

            viewModelScope.launch {
                moviesLoading.postValue(true)
                repository.searchMovie(querytring = searchString).apply {
                    moviesLoading.postValue(false)

                    if (this.isSuccessful){
                        movies.postValue(this.body())
                    }else{
                        moviesyerorr.postValue(true)
                    }
                }
            }
        }
    }
}