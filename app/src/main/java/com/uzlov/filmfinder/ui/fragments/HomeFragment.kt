package com.uzlov.filmfinder.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.uzlov.filmfinder.app.App
import com.uzlov.filmfinder.databinding.FragmentHomeBinding
import com.uzlov.filmfinder.mvp.model.image.IImageLoader
import com.uzlov.filmfinder.mvp.presenters.HomePresenter
import com.uzlov.filmfinder.ui.adapters.FilmsAdapter
import com.uzlov.filmfinder.ui.view.BackButtonListener
import com.uzlov.filmfinder.ui.view.HomeView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class HomeFragment : MvpAppCompatFragment(), HomeView, SharedPreferences.OnSharedPreferenceChangeListener, BackButtonListener{

    private var enableAdult: Boolean = false
    private var _viewBinding: FragmentHomeBinding? = null
    private val viewBinding get() = _viewBinding!!

    @Inject
    lateinit var glideImageLoader: IImageLoader<ImageView>

    private val homePresenter by moxyPresenter {
        HomePresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (count == 0) {
                viewBinding.scrollContainer.visibility = View.VISIBLE
                viewBinding.searchRV.visibility = View.GONE
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
        PreferenceManager.getDefaultSharedPreferences(requireContext()).registerOnSharedPreferenceChangeListener(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        PreferenceManager.getDefaultSharedPreferences(requireContext()).registerOnSharedPreferenceChangeListener(this)
    }

    private fun initListeners() {
        with(viewBinding){
            viewBinding.nameFilmSearchTv.addTextChangedListener(textWatcher)
        }
    }

    override fun backPressed(): Boolean {
        homePresenter.backPressed()
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
        PreferenceManager.getDefaultSharedPreferences(requireContext()).unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sPref: SharedPreferences?, key: String) {
        when(key){
            "adult" -> {
                enableAdult = sPref?.getBoolean(key, false) ?: false
            }
        }
    }

    override fun init() {
        with(viewBinding){
            popularRV.adapter = FilmsAdapter(homePresenter.popularListPresenter, glideImageLoader)
            upcomingRV.adapter = FilmsAdapter(homePresenter.upcomingListPresenter, glideImageLoader)
            recommendRV.adapter = FilmsAdapter(homePresenter.topListPresenter, glideImageLoader)
        }
    }


    override fun loadPopularFilms() {
        with(viewBinding){
            popularRV.adapter?.notifyDataSetChanged()
        }
    }

    override fun loadTopFilms() {
        with(viewBinding){
            recommendRV.adapter?.notifyDataSetChanged()
        }
    }

    override fun loadUpcomingFilms() {
        with(viewBinding){
            upcomingRV.adapter?.notifyDataSetChanged()
        }
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}

