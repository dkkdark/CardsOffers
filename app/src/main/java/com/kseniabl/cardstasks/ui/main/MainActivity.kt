package com.kseniabl.cardstasks.ui.main

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.cardview.widget.CardView
import com.kseniabl.cardtasks.R
import com.kseniabl.cardstasks.ui.base.BaseActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import com.kseniabl.cardstasks.ui.show_item.ShowItemActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.NavHostFragment
import com.kseniabl.cardstasks.ui.base.FreelancerModel
import com.kseniabl.cardstasks.ui.models.CardModel
import com.kseniabl.cardstasks.ui.freelancer_details.FreelancerDetailsActivity
import com.kseniabl.cardtasks.ui.main.MainPresenterInterface

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

    private fun setUpToolbarNavigation() {
        bottom_navigation.setOnItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.all_prods -> setAllProdFragment(null)
                    R.id.add_prod -> setAddProdFragment()
                    R.id.settings -> setSettingsFragment()
                    R.id.chats -> setChatFragment()
                }
                true
            }
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
        //overridePendingTransition(R.anim.fade_out, R.anim.fade_in)
    }

    override fun openFreelancerDetailsActivity(item: FreelancerModel) {
        val intent = Intent(this, FreelancerDetailsActivity::class.java)
        intent.putExtra("item", item)
        startActivity(intent)
        //overridePendingTransition(R.anim.fade_out, R.anim.fade_in)
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
        val bundle = Bundle()
        bundle.putString("fragment", fragment)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navHostFragment.navController.navigate(R.id.allOffersFragment, bundle)
    }

    private fun setSettingsFragment() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navHostFragment.navController.navigate(R.id.settingsFragmnet)
    }

    private fun setAddProdFragment() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navHostFragment.navController.navigate(R.id.addTasksFragment)
    }

    private fun setChatFragment() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navHostFragment.navController.navigate(R.id.chatListFragment)
    }
}