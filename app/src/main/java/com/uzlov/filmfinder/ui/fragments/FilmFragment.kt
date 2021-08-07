package com.uzlov.filmfinder.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.uzlov.filmfinder.databinding.FragmentFilmBinding
import com.uzlov.filmfinder.mvp.model.entity.Film

class FilmFragment : Fragment() {
    private var _viewBinding: FragmentFilmBinding? = null
    private val viewBinding get() = _viewBinding!!
    private var isAddedFavorite = false


    private var idFilm  = 0
    private lateinit var film: Film

    companion object {
        fun newInstance(id: Int): FilmFragment {
            val data = Bundle().apply {
                putInt("film_key", id)
            }
            return FilmFragment().apply { arguments = data }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idFilm = arguments?.getInt("film_key") ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentFilmBinding.inflate(layoutInflater, container, false)
        return  viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.recyclerViewActor.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            //addItemDecoration(MyItemDecorator(RecyclerView.HORIZONTAL), RecyclerView.HORIZONTAL)
        }


        initListenerActionsFilm()
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

        viewBinding.shareBtn.setOnClickListener {
//            val intent = Intent(requireContext(), ShareActivity::class.java)
//            intent.apply {
//                putExtra("key_name", film.title)
//                putExtra("key_url", film.homepage)
//            }
//            startActivity(intent)
        }
    }



    private fun showLoading() {}

    private fun showError(error: Throwable) {
        Snackbar.make(viewBinding.root, error.message ?: "Empty error", Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }


}

interface OpenMapListener {
    fun open(id: Int)
}