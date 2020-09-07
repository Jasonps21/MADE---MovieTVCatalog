package com.jason.movietvcatalog.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jason.movietvcatalog.R
import com.jason.movietvcatalog.`interface`.OnItemClickCallback
import com.jason.movietvcatalog.core.BuildConfig
import com.jason.movietvcatalog.core.domain.model.Movie
import kotlinx.android.synthetic.main.items_movie.view.*
import java.util.*

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvshowViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvshowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_movie, parent, false)
        return TvshowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvshowViewHolder, position: Int) {
        val tvShowData = listData[position]
        holder.bind(tvShowData)

    }

    inner class TvshowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvshow: Movie) {
            with(itemView) {
                Glide.with(context)
                    .load(BuildConfig.BASE_URL_IMAGE + tvshow.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(img_poster)
                setOnClickListener { onItemClickCallback?.onItemClicked(tvshow) }
            }
        }
    }

    override fun getItemCount(): Int = listData.size
}