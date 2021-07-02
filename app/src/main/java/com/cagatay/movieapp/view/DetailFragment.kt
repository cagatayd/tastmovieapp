package com.cagatay.movieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.cagatay.movieapp.R
import com.cagatay.movieapp.adapter.MovieAdapter
import com.cagatay.movieapp.databinding.FragmentDetailBinding
import com.cagatay.movieapp.databinding.FragmentMainBinding
import com.cagatay.movieapp.util.GlideHelper
import com.cagatay.movieapp.viewmodel.DetailViewmodel
import com.cagatay.movieapp.viewmodel.MainViewmodel


class DetailFragment : Fragment() {

    private lateinit var movietitle:String
    private var fragmentbinding : FragmentDetailBinding?=null
    lateinit var viewmodel : DetailViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel= ViewModelProvider(requireActivity()).get(DetailViewmodel::class.java)

        val binding=FragmentDetailBinding.bind(view)
        fragmentbinding=binding


           arguments.let {
               movietitle=DetailFragmentArgs.fromBundle(it!!).title

           }

        lifecycleScope.launchWhenCreated {
            viewmodel.searchMoviedetail(movietitle)
        }
        observeLiveData()

    }







    fun observeLiveData(){
        viewmodel.moviesdetail.observe(viewLifecycleOwner, Observer {
            it?.let {

                fragmentbinding?.txtDescriptions?.text=it.Plot
                fragmentbinding?.txtCountry?.text=it.Country
                fragmentbinding?.txtDirector?.text=it.Director
                fragmentbinding?.txtGenre?.text=it.Genre
                fragmentbinding?.txtLanguage?.text=it.Language
                fragmentbinding?.txtRuntime?.text=it.Runtime
                fragmentbinding?.txtPosterTitle?.text=it.Title
                fragmentbinding?.txtPosterActors?.text=it.Actors
                fragmentbinding?.txtPosterWriter?.text=it.Writer
                fragmentbinding?.txtReleased?.text=it.Released
                fragmentbinding?.txtYear?.text=it.Year

                GlideHelper.loadImage(requireContext(),it.Poster,fragmentbinding!!.imgDetailPoster)




            }
        })

        viewmodel.moviesyerorrdetail.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {

                    fragmentbinding?.txtErorrdetail?.visibility = View.VISIBLE
                    fragmentbinding?.progressBardetail?.visibility = View.GONE
                } else{
                    fragmentbinding?.txtErorrdetail?.visibility = View.GONE
                    fragmentbinding?.progressBardetail?.visibility = View.GONE
                }

            }
        })

        viewmodel.moviesLoadingdetail.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    fragmentbinding?.progressBardetail?.visibility = View.VISIBLE
                } else {
                    fragmentbinding?.progressBardetail?.visibility = View.GONE

                }
            }
        })
    }





}