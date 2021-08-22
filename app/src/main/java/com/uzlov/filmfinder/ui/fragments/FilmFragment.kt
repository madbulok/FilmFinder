package com.uzlov.filmfinder.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.uzlov.filmfinder.app.App
import com.uzlov.filmfinder.app.Constants
import com.uzlov.filmfinder.databinding.FragmentFilmBinding
import com.uzlov.filmfinder.mvp.model.entity.Credits
import com.uzlov.filmfinder.mvp.model.entity.Film
import com.uzlov.filmfinder.mvp.model.image.IImageLoader
import com.uzlov.filmfinder.mvp.presenters.FilmPresenter
import com.uzlov.filmfinder.ui.adapters.ActorAdapter
import com.uzlov.filmfinder.ui.view.BackButtonListener
import com.uzlov.filmfinder.ui.view.FilmView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class FilmFragment : MvpAppCompatFragment(), FilmView, BackButtonListener {
    private var _viewBinding: FragmentFilmBinding? = null
    private val viewBinding get() = _viewBinding!!
    private var isAddedFavorite = false
    private var filmId: Int = -1


    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    private val presenter by moxyPresenter {
        filmId = arguments?.getInt(Constants.KEY_FILM_ENTITY) ?: -1
        FilmPresenter(filmId).apply {
            App.instance.appComponent.inject(this)
        }
    }

    companion object {
        fun newInstance(film: Int): FilmFragment {
            val data = bundleOf(Constants.KEY_FILM_ENTITY to film)
            return FilmFragment().apply { arguments = data }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        App.instance.appComponent.inject(this)
        _viewBinding = FragmentFilmBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.recyclerViewActor.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            //addItemDecoration(MyItemDecorator(RecyclerView.HORIZONTAL), RecyclerView.HORIZONTAL)
        }
    }

    override fun init() {
        initListenerActionsFilm()
        with(viewBinding){
            recyclerViewActor.adapter = ActorAdapter(presenter.actorsPresenter, imageLoader)
        }
    }

    private fun initListenerActionsFilm() {
        viewBinding.favoriteBtn.setOnClickListener {
            when(isAddedFavorite){
                true->{ }
                false->{ }
            }
        }
        viewBinding.backButton.setOnClickListener {
            backPressed()
        }
    }

    override fun loadFilm(film: Film) {
        with(viewBinding){
            favoriteBtn.visibility = View.VISIBLE
            titleTv.text = film.title
            ratingFilm.rating = film.vote_average.div(2).toFloat()
            descriptionTV.text = film.overview

            if (film.production_companies.isNotEmpty()) studioFilm.text = film.production_companies[0].name
            yearFilmTv.text = film.release_date

            if (film.adult) titleTv.append(" 18+")

            film.genres.map {
                genreFilmTv.append("${it.name} \n")
            }

            countVoteTV.text = film.vote_count.toString()
            imageLoader.loadInto(film.getImageOriginal(), image)
        }
    }

    override fun loadActors(credits: Credits) {
        with(viewBinding){
            recyclerViewActor.adapter?.notifyDataSetChanged()
        }
    }

    override fun showError(message: String) {
        Snackbar.make(viewBinding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun backPressed(): Boolean = presenter.backButton()

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}