package com.jason.movietvcatalog.core.ui

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

class FavoriteTvShowAdapter : RecyclerView.Adapter<FavoriteTvShowAdapter.TvShowViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_movie, parent, false)
        return TvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = listData[position]
        holder.bind(tvShow)
    }

    inner class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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