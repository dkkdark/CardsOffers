package com.kseniabl.cardsmarket.ui.main

import android.R.attr
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.cardview.widget.CardView
import androidx.fragment.app.commit
import com.kseniabl.cardsmarket.R
import com.kseniabl.cardsmarket.ui.base.BaseActivity
import com.kseniabl.cardsmarket.ui.settings.SettingsFragmnet
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import androidx.drawerlayout.widget.DrawerLayout
import com.kseniabl.cardsmarket.ui.add_prod.AddTasksFragment
import com.kseniabl.cardsmarket.ui.all_prods.AllOffersFragment
import com.kseniabl.cardsmarket.ui.login.LoginActivity
import com.kseniabl.cardsmarket.ui.show_item.ShowItemActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import com.kseniabl.cardsmarket.ui.freelancer_details.FreelancerDetailsActivity
import com.kseniabl.cardsmarket.ui.models.CardModel
import android.R.attr.key

class MainActivity: BaseActivity(), MainView, HasAndroidInjector {

    @Inject
    lateinit var presenter: MainPresenterInterface<MainView>

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val whatFragment = intent.extras?.getString("whatFragment")

        setAllProdFragment(whatFragment)
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

    /*private fun changeToolbarUpOrHamburger() {
        supportFragmentManager.addOnBackStackChangedListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view)
            if (fragment is ShowItemActivity) {
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
    }*/

    private fun setUpToolbarNavigation() {
        bottom_navigation.setOnItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.all_prods -> {
                        setAllProdFragment(null)
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

    override fun openShowItemActivity(card: CardModel, cardView: CardView) {
        val intent = Intent(this, ShowItemActivity::class.java)
        intent.putExtra("card", card)
        intent.putExtra("transName", ViewCompat.getTransitionName(cardView))
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            cardView,
            ViewCompat.getTransitionName(cardView)!!)
        startActivity(intent, options.toBundle())
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in)
    }

    override fun openFreelancerDetailsActivity() {
        val intent = Intent(this, FreelancerDetailsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setAllProdFragment(fragment: String?) {
        val allOffersFragment = AllOffersFragment.newInstance()
        val bundle = Bundle()
        bundle.putString("fragment", fragment)
        allOffersFragment.arguments = bundle
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            replace(R.id.fragment_container_view, allOffersFragment)
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