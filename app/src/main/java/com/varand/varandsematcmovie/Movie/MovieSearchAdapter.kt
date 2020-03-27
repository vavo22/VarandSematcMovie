package com.varand.varandsematcmovie.Movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.varand.varandsematcmovie.R
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MovieSearchAdapter(private val list: List<Search>, private val listener: (String) -> Unit) :
    RecyclerView.Adapter<MovieSearchAdapter.MovieSearchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSearchViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return MovieSearchViewHolder(v,listener)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MovieSearchViewHolder, position: Int) {
        holder.bind(list[position])
    }


    class MovieSearchViewHolder(itemView: View, private val listener: (String) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Search) {

            itemView.movie_name.text = movie.Title
            itemView.movie_name.text = movie.Title
            Picasso.get().load(movie.Poster).into(itemView.Movie_cover)

            itemView.setOnClickListener {
                listener(movie.imdbID)
            }
        }

    }
}