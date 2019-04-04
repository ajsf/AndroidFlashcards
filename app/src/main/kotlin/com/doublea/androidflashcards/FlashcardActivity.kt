package com.doublea.androidflashcards

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.doublea.androidflashcards.extensions.launchFragment
import com.doublea.androidflashcards.model.Flashcard
import com.doublea.androidflashcards.repository.Repository
import com.doublea.androidflashcards.ui.FlashcardListFragment
import com.doublea.androidflashcards.viewmodel.FlashcardViewModel
import kotlinx.android.synthetic.main.activity_flashcard.*
import kotlinx.android.synthetic.main.app_bar_flashcard.*
import javax.inject.Inject

class FlashcardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var repository: Repository<Flashcard>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashcard)
        setSupportActionBar(toolbar)

        (application as MainApplication).component.inject(this)

        val vm = FlashcardViewModel.create(this)
        vm.repository = repository

        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            FlashcardListFragment().launchFragment(supportFragmentManager, addToBackStack = false)
        }
    }

    override fun onSupportNavigateUp(): Boolean = supportFragmentManager.popBackStack().run { true }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.flashcard, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
