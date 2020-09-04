package com.jason.movietvcatalog.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jason.movietvcatalog.`interface`.OnItemClickCallback
import com.jason.movietvcatalog.core.BuildConfig
import com.jason.movietvcatalog.core.R
import com.jason.movietvcatalog.core.domain.model.Movie
import kotlinx.android.synthetic.main.items_movie.view.*
import java.util.*

class FavoriteMovieAdapter : RecyclerView.Adapter<FavoriteMovieAdapter.MovieViewHolder>() {

    private var listData = ArrayList<Movie>()

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listData[position]
        holder.bind(movie)
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            with(itemView) {
                Glide.with(context)
                    .load(BuildConfig.BASE_URL_IMAGE + movie.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(img_poster)
                setOnClickListener { onItemClickCallback?.onItemClicked(movie) }
            }
        }
    }

    override fun getItemCount(): Int = listData.size
}