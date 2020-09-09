package com.jason.movietvcatalog.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.jason.movietvcatalog.R
import com.jason.movietvcatalog.core.BuildConfig
import com.jason.movietvcatalog.core.data.source.Resource
import com.jason.movietvcatalog.core.domain.model.Movie
import com.jason.movietvcatalog.ui.adapter.DetailMovieAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModel()

    private var category = 0
    private var statusFavorite = false

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_CATEGORY = "extra_category"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = DetailMovieAdapter()
        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(EXTRA_MOVIE)
            category = extras.getInt(EXTRA_CATEGORY)

            viewModel.setSelectedMovie(movieId)
            viewModel.actors.observe(this, { actorResource ->
                if (actorResource != null) {
                    when (actorResource) {
                        is Resource.Loading -> progress_bar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            if (actorResource.data != null) {
                                adapter.setActor(actorResource.data)
                                adapter.notifyDataSetChanged()
                            }
                            progress_bar.visibility = View.GONE
                        }
                        is Resource.Error -> {
                            progress_bar.visibility = View.GONE
                            Log.e("ACTOR_RESPONSE", "ACTOR NOT FOUND")
                        }
                    }
                    progress_bar.visibility = View.GONE
                }
            })
            when (category) {
                R.string.movie ->
                    viewModel.movieDetail.observe(this, { movieDetail ->
                        if (movieDetail != null) {
                            when (movieDetail) {
                                is Resource.Loading -> progress_bar.visibility = View.VISIBLE
                                is Resource.Success -> if (movieDetail.data != null) {
                                    progress_bar.visibility = View.GONE
                                    populateMovie(movieDetail.data!!)
                                    val state = movieDetail.data!!.isFavorite
                                    statusFavorite = state
                                    setFavoriteMovie(state)
                                }
                                is Resource.Error -> {
                                    progress_bar.visibility = View.GONE
                                    Toast.makeText(
                                        applicationContext,
                                        "Terjadi kesalahan",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    })
                R.string.tvshow ->
                    viewModel.tvShowDetail.observe(this, { tvShowDetail ->
                        if (tvShowDetail != null) {
                            when (tvShowDetail) {
                                is Resource.Loading -> progress_bar.visibility = View.VISIBLE
                                is Resource.Success -> if (tvShowDetail.data != null) {
                                    progress_bar.visibility = View.GONE
                                    populateMovie(tvShowDetail.data!!)
                                    val state = tvShowDetail.data!!.isFavorite
                                    statusFavorite = state
                                    setFavoriteMovie(state)
                                }
                                is Resource.Error -> {
                                    progress_bar.visibility = View.GONE
                                    Toast.makeText(
                                        applicationContext,
                                        "Terjadi kesalahan",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    })

            }
        }
        with(rv_actor) {
            layoutManager =
                LinearLayoutManager(this@DetailMovieActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            this.adapter = adapter
        }
    }

    private fun populateMovie(movieEntity: Movie) {
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = movieEntity.name
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            if (!statusFavorite) {
                Snackbar.make(view, getString(R.string.text_favorite), Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.head_title_dialog), null).show()
            } else {
                Snackbar.make(view, getString(R.string.text_unfavorite), Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.head_title_dialog), null).show()
            }
            viewModel.setFavorite(category)
        }
        Glide.with(this@DetailMovieActivity)
            .load(BuildConfig.BASE_URL_IMAGE + movieEntity.backdrop)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(img_backdrop)
        Glide.with(this@DetailMovieActivity)
            .load(BuildConfig.BASE_URL_IMAGE + movieEntity.posterPath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(img_poster)
        sinopsis.text = movieEntity.overview
        genre.text = movieEntity.genre
        release_year.text = movieEntity.releaseDate
        duration.text = movieEntity.runtime?.let { convertDuration(it) }
        status.text = movieEntity.status
    }

    private fun setFavoriteMovie(state: Boolean) {
        if (state) {
            fab.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            fab.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    private fun convertDuration(t: Int): String {
        val hours: Int = t / 60
        val minutes: Int = t % 60
        return if (t > 60) String.format("%dh %02dm", hours, minutes) else String.format(
            "%02dm",
            minutes
        )
    }
}