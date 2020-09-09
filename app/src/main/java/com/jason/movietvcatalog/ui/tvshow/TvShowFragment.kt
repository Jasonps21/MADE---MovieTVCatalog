package com.jason.movietvcatalog.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.jason.movietvcatalog.R
import com.jason.movietvcatalog.`interface`.OnItemClickCallback
import com.jason.movietvcatalog.core.data.source.Resource
import com.jason.movietvcatalog.core.domain.model.Movie
import com.jason.movietvcatalog.ui.adapter.TvShowAdapter
import com.jason.movietvcatalog.ui.detail.DetailMovieActivity
import kotlinx.android.synthetic.main.fragment_tvshow.*
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {

    lateinit var nav: NavController
    private val viewModel: TvShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nav = Navigation.findNavController(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val tvshowAdapter = TvShowAdapter()
            progress_bar.visibility = View.VISIBLE
            viewModel.setSearchTv("")
            viewModel.getTvshow.observe(viewLifecycleOwner, { tvShows ->
                if (tvShows != null) {
                    when (tvShows) {
                        is Resource.Loading -> progress_bar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            progress_bar.visibility = View.GONE
                            tvshowAdapter.setData(tvShows.data)
                            tvshowAdapter.notifyDataSetChanged()
                        }
                        is Resource.Error -> {
                            progress_bar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            tvshowAdapter.setOnItemClickCallback(object : OnItemClickCallback {
                override fun onItemClicked(data: Movie) {
                    val bundle = Bundle()
                    bundle.putInt(DetailMovieActivity.EXTRA_MOVIE, data.id)
                    bundle.putInt(DetailMovieActivity.EXTRA_CATEGORY, R.string.tvshow)
                    nav.navigate(R.id.action_nav_tv_show_to_detailMovieActivity, bundle)
                }
            })

            with(rv_tvshow) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = tvshowAdapter
            }

            searchViewTvShow.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    viewModel.setSearchTv(query)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
    }
}