package com.jason.movietvcatalog.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jason.movietvcatalog.R
import com.jason.movietvcatalog.core.BuildConfig
import com.jason.movietvcatalog.core.domain.model.People
import kotlinx.android.synthetic.main.items_people.view.*
import java.util.*

class PeopleAdapter : RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>() {

    private var listData = ArrayList<People>()

    fun setData(newListData: List<People>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_people, parent, false)
        return PeopleViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        val people = listData[position]
        holder.bind(people)

    }

    inner class PeopleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(people: People) {
            with(itemView) {
                Glide.with(context)
                    .load(BuildConfig.BASE_URL_IMAGE + people.profilePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(profile_picture_people)
                name_people.text = people.name
                know_for_departement.text = people.knownForDepartment
            }
        }
    }

    override fun getItemCount(): Int = listData.size
}