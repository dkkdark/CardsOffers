package com.kseniabl.cardsmarket.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.commit
import com.kseniabl.cardsmarket.R
import com.kseniabl.cardsmarket.ui.add_prod.AddProdFragment
import com.kseniabl.cardsmarket.ui.all_prods.AllProdsFragment
import com.kseniabl.cardsmarket.ui.base.BaseActivity
import com.kseniabl.cardsmarket.ui.settings.SettingsFragmnet
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationBarView
import com.kseniabl.cardsmarket.ui.add_prod.AddTasksFragment
import com.kseniabl.cardsmarket.ui.all_prods.AllOffersFragment
import com.kseniabl.cardsmarket.ui.show_item.ShowItemFragment
import kotlinx.android.synthetic.main.navigation_drawer.*


class MainActivity: BaseActivity(), MainView, HasAndroidInjector {

    @Inject
    lateinit var presenter: MainPresenterInterface<MainView>

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setAllProdFragment()
        setUpToolbarNavigation()
        presenter.attachView(this)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    override fun hideLoadProgress() {
        TODO("Not yet implemented")
    }

    override fun showLoadProgress() {
        TODO("Not yet implemented")
    }

    private fun changeToolbarUpOrHamburger() {
        supportFragmentManager.addOnBackStackChangedListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view)
            if (fragment is ShowItemFragment) {
                drawerToggle.isDrawerIndicatorEnabled = false
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                toolbar.setNavigationOnClickListener { onBackPressed() }
            }
            else {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                drawerToggle.isDrawerIndicatorEnabled = true
                drawerToggle.toolbarNavigationClickListener = null
                drawerToggle.syncState()
                toolbar.setNavigationOnClickListener {
                    drawerLayout.open()
                }
            }
        }
    }

    private fun setUpToolbarNavigation() {
        bottom_navigation.setOnItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.all_prods -> {
                        setAllProdFragment()
                    }
                    R.id.add_prod -> {
                        setAddProdFragment()
                    }
                    R.id.settings -> {
                        setSettingsFragment()
                    }
                }
                true
            }
    }

    private fun setBottomNavigationBar() {
        setSupportActionBar(toolbar)
        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        drawerLayout.addDrawerListener(drawerToggle)
        drawerLayout.setScrimColor(Color.TRANSPARENT)

        toolbar.setNavigationOnClickListener {
            drawerLayout.open()
        }

        //changeToolbarUpOrHamburger()
        setUpToolbarNavigation()
    }

    private fun setAllProdFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            replace(R.id.fragment_container_view, AllOffersFragment.newInstance())
        }
    }

    private fun setSettingsFragment() {
        supportFragmentManager.commit {
            setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            setReorderingAllowed(true)
            replace(R.id.fragment_container_view, SettingsFragmnet.newInstance())
        }
    }

    private fun setAddProdFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            replace(R.id.fragment_container_view, AddTasksFragment.newInstance())
        }
    }
}