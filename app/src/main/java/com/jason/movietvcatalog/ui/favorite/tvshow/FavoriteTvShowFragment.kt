package com.jason.movietvcatalog.ui.favorite.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.jason.movietvcatalog.R
import com.jason.movietvcatalog.`interface`.OnItemClickCallback
import com.jason.movietvcatalog.core.domain.model.Movie
import com.jason.movietvcatalog.ui.adapter.FavoriteTvShowAdapter
import com.jason.movietvcatalog.ui.detail.DetailMovieActivity
import kotlinx.android.synthetic.main.fragment_favorite_movie.*
import kotlinx.android.synthetic.main.fragment_tvshow.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteTvShowFragment : Fragment() {

    private val viewModel: FavoriteTvShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val tvShowAdapter = FavoriteTvShowAdapter()
            viewModel.getTvShowsFavorite().observe(viewLifecycleOwner, Observer { tvShows ->
                tvShowAdapter.setData(tvShows)
                tvShowAdapter.notifyDataSetChanged()
                view_empty.visibility = if (tvShows.isNotEmpty()) View.GONE else View.VISIBLE
            })

            tvShowAdapter.setOnItemClickCallback(object : OnItemClickCallback {
                override fun onItemClicked(data: Movie) {
                    val intent = Intent(requireActivity(), DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, data.id)
                    intent.putExtra(DetailMovieActivity.EXTRA_CATEGORY, R.string.tvshow)
                    startActivity(intent)
                }
            })

            with(rv_tvshow) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }
}