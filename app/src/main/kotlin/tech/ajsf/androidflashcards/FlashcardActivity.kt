package tech.ajsf.androidflashcards

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_flashcard.*
import kotlinx.android.synthetic.main.app_bar_flashcard.*
import tech.ajsf.androidflashcards.extensions.launchFragment
import tech.ajsf.androidflashcards.model.Category
import tech.ajsf.androidflashcards.model.Flashcard
import tech.ajsf.androidflashcards.repository.Repository
import tech.ajsf.androidflashcards.ui.FlashcardListFragment
import tech.ajsf.androidflashcards.viewmodel.FlashcardViewModel
import javax.inject.Inject

class FlashcardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var repository: Repository<Flashcard>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashcard)
        setSupportActionBar(toolbar)

        (application as MainApplication).component.inject(this)

        initViewModel()
        initUi()

        if (savedInstanceState == null) {
            launchFragment()
        }
    }

    private fun initViewModel() = FlashcardViewModel.create(this)
        .also { it.repository = repository }

    private fun initUi() {
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
    }

    private fun launchFragment() = FlashcardListFragment()
        .launchFragment(supportFragmentManager, addToBackStack = false)

    override fun onSupportNavigateUp(): Boolean = supportFragmentManager
        .popBackStack().run { true }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.flashcard, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val category = getCategory(item.itemId)
        scrollToCategory(category)
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun getCategory(itemId: Int): Category = when (itemId) {
        R.id.nav_android -> Category.ANDROID
        R.id.nav_java -> Category.JAVA
        R.id.nav_algo -> Category.DATA
        R.id.nav_architecture -> Category.ARCHITECTURE
        R.id.nav_design -> Category.DESIGN
        R.id.nav_tools -> Category.TOOLS
        R.id.nav_test -> Category.TESTING
        R.id.nav_other -> Category.OTHER
        else -> Category.ANDROID
    }

    private fun scrollToCategory(category: Category) {
        while (getFragment() !is FlashcardListFragment) {
            popBackStack()
        }
        (getFragment() as FlashcardListFragment).scrollToCategory(category)
    }

    private fun getFragment() = supportFragmentManager.findFragmentById(R.id.fragment_container)

    private fun popBackStack() = supportFragmentManager.popBackStackImmediate()
}
