package com.jason.movietvcatalog.people

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.jason.movietvcatalog.core.data.source.Resource
import com.jason.movietvcatalog.ui.adapter.PeopleAdapter
import com.jason.people.R
import kotlinx.android.synthetic.main.activity_people.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class PeopleActivity : AppCompatActivity() {

    private val viewModel: PeopleViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people)
        loadKoinModules(peopleModule)
        supportActionBar?.title = "People"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val peopleAdapter = PeopleAdapter()
        progress_bar.visibility = View.VISIBLE
        viewModel.getPeople().observe(this, Observer{ peoples ->
            if (peoples != null){
                when(peoples){
                    is Resource.Loading -> progress_bar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        progress_bar.visibility = View.GONE
                        peopleAdapter.setData(peoples.data)
                        peopleAdapter.notifyDataSetChanged()
                    }
                    is Resource.Error -> {
                        progress_bar.visibility = View.GONE
                        Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        with(rv_people) {
            layoutManager = GridLayoutManager(context,2)
            setHasFixedSize(true)
            adapter = peopleAdapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}