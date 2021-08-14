package com.uzlov.filmfinder.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.uzlov.filmfinder.databinding.ItemHomeFilmBinding
import com.uzlov.filmfinder.mvp.model.image.IImageLoader
import com.uzlov.filmfinder.mvp.presenters.list.IFilmsListPresenter
import com.uzlov.filmfinder.ui.view.list.films.FilmItemView

class FilmsAdapter(private val presenter: IFilmsListPresenter, val imageLoader: IImageLoader<ImageView>) : RecyclerView.Adapter<FilmsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemHomeFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(private val vb: ItemHomeFilmBinding) : RecyclerView.ViewHolder(vb.root),
        FilmItemView {
        override fun setTitle(text: String) {
            vb.nameFilm.text = text
        }

        override fun loadPoster(url: String) {
            imageLoader.loadInto(url, vb.iamgeFilm)
        }

        override fun setRating(countStar: Float) {
            vb.rateFilm.rating = countStar.div(2)
        }

        override var pos = -1
    }
}