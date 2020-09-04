package com.jason.movietvcatalog.discussions

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.jason.movietvcatalog.core.data.source.Resource
import kotlinx.android.synthetic.main.activity_discussion.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class DiscussionActivity : AppCompatActivity() {

    private val discussionViewModel: DiscussionViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discussion)
        loadKoinModules(discussionModule)
        supportActionBar?.title = "Discussion Movie"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getMovieData()
    }

    private fun getMovieData() {
        discussionViewModel.movie.observe(this, Observer { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> progress_bar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        progress_bar.visibility = View.GONE
                        tv_maps.text = "This is dissucion of ${movie.data?.get(0)?.name}"
                    }
                    is Resource.Error -> {
                        progress_bar.visibility = View.GONE
                        tv_error.visibility = View.VISIBLE
                        tv_error.text = movie.message
                    }
                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}