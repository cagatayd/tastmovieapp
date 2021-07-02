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
class DetailViewmodel @Inject constructor(
    private val repository: repository
) :ViewModel() {


    val moviesdetail = MutableLiveData<BaseResponse>()
    val moviesyerorrdetail= MutableLiveData<Boolean>()
    val moviesLoadingdetail= MutableLiveData<Boolean>()


    fun searchMoviedetail(searchString: String){
        if (searchString.isNotEmpty()){

            viewModelScope.launch {
                moviesLoadingdetail.postValue(true)
                repository.searchMovieDetail(querytring = searchString).apply {
                    moviesLoadingdetail.postValue(false)

                    if (this.isSuccessful){
                        moviesdetail.postValue(this.body())
                    }else{
                        moviesyerorrdetail.postValue(true)
                    }
                }
            }
        }
    }
}