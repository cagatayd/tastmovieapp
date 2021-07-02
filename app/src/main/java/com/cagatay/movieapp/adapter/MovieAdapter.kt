package com.cagatay.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cagatay.movieapp.R
import com.cagatay.movieapp.model.Search
import com.cagatay.movieapp.util.GlideHelper
import com.cagatay.movieapp.view.MainFragmentDirections


class MovieAdapter:RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(),OnClicklistener {

    class MovieViewHolder(itemview: View):RecyclerView.ViewHolder(itemview) {

    }



    private val diffUtil = object : DiffUtil.ItemCallback<Search>(){ // en ufak güncellemeleri recyleviewde güncellmek için
        override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean { // itemlerı aynı mmı

            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean { // içerikleri aynı mı?
            return oldItem==newItem
        }

    }
    private val recylelistdiffer= AsyncListDiffer(this,diffUtil)

    var movielist:List<Search>

        get() = recylelistdiffer.currentList
        set(value) = recylelistdiffer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.movie_row,parent,false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {


        val postertitle=holder.itemView.findViewById<TextView>(R.id.postertitle)
        var poster=holder.itemView.findViewById<ImageView>(R.id.poster)


        val movies=movielist[position]
        holder.itemView.setOnClickListener {
            onCountryClicked(holder.itemView,movies)
        }

        holder.itemView.apply {
            postertitle.text=movies.Title
            GlideHelper.loadImage(context,movies.Poster,poster)


        }



    }

    override fun getItemCount(): Int {

        return movielist.size
    }

    override fun onCountryClicked(v: View, movies: Search) {

     val action=MainFragmentDirections.actionMainFragmentToDetailFragment(movies.Title)
        Navigation.findNavController(v).navigate(action)






    }
}