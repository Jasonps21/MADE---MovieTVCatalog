package com.jason.movietvcatalog.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jason.movietvcatalog.R
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        val sectionsPagerAdapter = SectionsPagerAdapterFavorite(this, supportFragmentManager)
        view_pager_favorite.adapter = sectionsPagerAdapter
        tabs_favorite.setupWithViewPager(view_pager_favorite)
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}