package com.uzlov.filmfinder.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.uzlov.filmfinder.databinding.ItemActorBinding
import com.uzlov.filmfinder.mvp.model.image.IImageLoader
import com.uzlov.filmfinder.mvp.presenters.list.IActorListPresenter
import com.uzlov.filmfinder.ui.view.list.actors.IActorItemView

class ActorAdapter (private val presenter: IActorListPresenter, val imageLoader: IImageLoader<ImageView>) : RecyclerView.Adapter<ActorAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemActorBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(private val vb: ItemActorBinding) : RecyclerView.ViewHolder(vb.root),
        IActorItemView {
        override fun setTitle(text: String) {
            vb.actorName.text = text
        }

        override fun loadPoster(url: String) {
            imageLoader.loadInto(url, vb.imageActor)
        }
        override var pos = -1
    }
}