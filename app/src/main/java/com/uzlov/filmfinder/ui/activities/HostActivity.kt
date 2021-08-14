package com.uzlov.filmfinder.ui.activities

import android.os.Bundle
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.uzlov.filmfinder.R
import com.uzlov.filmfinder.app.App
import com.uzlov.filmfinder.databinding.ActivityMainBinding
import com.uzlov.filmfinder.mvp.presenters.MainPresenter
import com.uzlov.filmfinder.ui.view.BackButtonListener
import com.uzlov.filmfinder.ui.view.MainView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class HostActivity :  MvpAppCompatActivity(), MainView {

    private var vb: ActivityMainBinding? = null
    private val navigator = AppNavigator(this, R.id.fragment_container)

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val presenter by moxyPresenter {
        MainPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        vb?.bottomNavigation?.setOnItemSelectedListener {
            when(it.itemId){
                R.id.action_favorite -> {
                    true
                }
                else -> false
            }
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()){
                return
            }
        }
        presenter.backClicked()
    }
}