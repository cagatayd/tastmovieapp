package com.cagatay.movieapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.cagatay.movieapp.R
import com.cagatay.movieapp.adapter.MovieAdapter
import com.cagatay.movieapp.databinding.FragmentMainBinding
import com.cagatay.movieapp.viewmodel.MainViewmodel
import javax.inject.Inject
import kotlin.math.log


class MainFragment : Fragment() {

    private var fragmentbinding : FragmentMainBinding?=null
    lateinit var viewmodel : MainViewmodel
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel=ViewModelProvider(requireActivity()).get(MainViewmodel::class.java)

        val binding=FragmentMainBinding.bind(view)
        fragmentbinding=binding

        fragmentbinding?.searcview?.setOnQueryTextListener(object  : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                Log.d("onQueryTextSubmit","click")
                return false

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                fragmentbinding!!.searcview.clearFocus()
                lifecycleScope.launchWhenCreated {
                    viewmodel.searchMovie(newText!!)
                }
                return true

            }

        })

        observeLiveData()


 }
    fun observeLiveData(){
        viewmodel.movies.observe(viewLifecycleOwner, Observer {
            it?.let {
                if(it.Response == "True"){

                    adapter=MovieAdapter()
                    adapter.movielist=it.Search
                    fragmentbinding?.recyleviewmain?.layoutManager=GridLayoutManager(requireContext(),2)
                    fragmentbinding?.recyleviewmain?.adapter = adapter

                    fragmentbinding?.txtErorr?.visibility = View.GONE
                    fragmentbinding?.progressBar?.visibility = View.GONE
                }else{
                    fragmentbinding?.txtErorr?.visibility = View.VISIBLE
                    fragmentbinding?.progressBar?.visibility = View.GONE
                }
            }
        })

        viewmodel.moviesyerorr.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {

                    fragmentbinding?.txtErorr?.visibility = View.VISIBLE
                    fragmentbinding?.progressBar?.visibility = View.GONE
                } else{
                    fragmentbinding?.txtErorr?.visibility = View.GONE
                    fragmentbinding?.progressBar?.visibility = View.GONE
                }

            }
        })

        viewmodel.moviesLoading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    fragmentbinding?.progressBar?.visibility = View.VISIBLE
                } else {
                    fragmentbinding?.progressBar?.visibility = View.GONE

                }
            }
        })
    }
}