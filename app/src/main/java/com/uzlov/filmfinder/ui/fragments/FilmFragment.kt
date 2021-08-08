package com.uzlov.filmfinder.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.uzlov.filmfinder.app.App
import com.uzlov.filmfinder.app.Constants
import com.uzlov.filmfinder.databinding.FragmentFilmBinding
import com.uzlov.filmfinder.mvp.model.entity.Film
import com.uzlov.filmfinder.mvp.presenters.FilmPresenter
import com.uzlov.filmfinder.ui.view.BackButtonListener
import com.uzlov.filmfinder.ui.view.FilmView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class FilmFragment : MvpAppCompatFragment(), FilmView, BackButtonListener {
    private var _viewBinding: FragmentFilmBinding? = null
    private val viewBinding get() = _viewBinding!!
    private var isAddedFavorite = false
    private var filmId: Int = -1


    private val presenter by moxyPresenter {
        filmId = arguments?.getInt(Constants.KEY_FILM_ENTITY) ?: -1
        FilmPresenter(filmId).apply {
            App.instance.appComponent.inject(this)
        }
    }

    companion object {
        fun newInstance(film: Int): FilmFragment {
            val data = Bundle().apply {
                putInt(Constants.KEY_FILM_ENTITY, film)
            }
            return FilmFragment().apply { arguments = data }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

    private fun initListenerActionsFilm() {
        viewBinding.favoriteBtn.setOnClickListener {
            when(isAddedFavorite){
                true->{
                }
                false->{

                }
            }
        }
    }

    override fun init() {
        initListenerActionsFilm()
    }

    override fun loadFilm(film: Film) {
        with(viewBinding){
            titleTv.text = film.title
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