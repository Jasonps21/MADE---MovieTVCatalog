package com.jason.movietvcatalog.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.jason.movietvcatalog.R
import com.jason.movietvcatalog.`interface`.OnItemClickCallback
import com.jason.movietvcatalog.core.data.source.Resource
import com.jason.movietvcatalog.core.domain.model.Movie
import com.jason.movietvcatalog.ui.adapter.MovieAdapter
import com.jason.movietvcatalog.ui.detail.DetailMovieActivity
import kotlinx.android.synthetic.main.fragment_movie.*
import org.koin.android.viewmodel.ext.android.viewModel


class MovieFragment : Fragment() {
    lateinit var nav: NavController
    private val viewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nav = Navigation.findNavController(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val movieAdapter = MovieAdapter()
            progress_bar.visibility = View.VISIBLE
            viewModel.setSearchMovie("")
            viewModel.getMovies.observe(viewLifecycleOwner, Observer { movies ->
                if (movies != null) {
                    when (movies) {
                        is Resource.Loading<*> -> progress_bar.visibility = View.VISIBLE
                        is Resource.Success<*> -> {
                            progress_bar.visibility = View.GONE
                            movieAdapter.setData(movies.data)
                            movieAdapter.notifyDataSetChanged()
                        }
                        is Resource.Error<*> -> {
                            progress_bar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            movieAdapter.setOnItemClickCallback(object : OnItemClickCallback {
                override fun onItemClicked(data: Movie) {
                    val bundle = Bundle()
                    bundle.putInt(DetailMovieActivity.EXTRA_MOVIE, data.id)
                    bundle.putInt(DetailMovieActivity.EXTRA_CATEGORY, R.string.movie)
                    nav.navigate(R.id.action_movieFragment_to_detailMovieActivity, bundle)
                }
            })

            with(rv_movies) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = movieAdapter
            }

            searchViewMovie.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    viewModel.setSearchMovie(query)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
    }
}
