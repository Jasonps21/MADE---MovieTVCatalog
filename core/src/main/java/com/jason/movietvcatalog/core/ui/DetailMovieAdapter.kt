package com.jason.movietvcatalog.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jason.movietvcatalog.core.BuildConfig
import com.jason.movietvcatalog.core.R
import com.jason.movietvcatalog.core.domain.model.Actor
import kotlinx.android.synthetic.main.items_actor.view.*

class DetailMovieAdapter : RecyclerView.Adapter<DetailMovieAdapter.ActorViewHolder>() {
    private val listActor = ArrayList<Actor>()
    fun setActor(actor: List<Actor>?) {
        if (actor == null) return
        listActor.clear()
        listActor.addAll(actor)
    }

    class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(actor: Actor) {
            with(itemView) {
                Glide.with(context)
                    .load(BuildConfig.BASE_URL_IMAGE + actor.profilePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.people)
                            .error(R.drawable.people))
                    .into(profile_picture)
                name_actor.text = actor.name
                name_player_actor.text = actor.character
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ActorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_actor, parent, false)
        return ActorViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = listActor.size

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        val module = listActor[position]
        holder.bind(module)
    }
}