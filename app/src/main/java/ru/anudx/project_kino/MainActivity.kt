package ru.anudx.project_kino

import android.os.Bundle
import android.view.Menu
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuAdapter
import androidx.appcompat.widget.SearchView
import androidx.navigation.ui.AppBarConfiguration
import ru.anudx.project_kino.databinding.ActivityMainBinding
import ru.anudx.project_kino.fragments.MainFragment
import ru.anudx.project_kino.study.StudyUserInputFragment

@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity() {
    val list = mutableListOf<String>()
    lateinit var menu: Menu
    lateinit var menuAdapter: SimpleCursorAdapter
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val b: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var timeBackPressed = 0L
    companion object {
        val TIME_INTERVAL_2S = 2000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(b.root)
        App.mainContext = this
        for (i in 0 .. 9) {
            list.add("item$i")
        }
    }
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            if (timeBackPressed + TIME_INTERVAL_2S > System.currentTimeMillis()) {
                super.onBackPressed()
                finish()
            } else {
                Toast.makeText(this, R.string.double_press_for_exit, Toast.LENGTH_SHORT).show()
            }
            timeBackPressed = System.currentTimeMillis()
        }else{
            super.onBackPressed()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.study_search_menu, menu)
        menu?.let { this.menu = it }
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, StudyUserInputFragment(), resources.getString(R.string.main_fragment_tag))
            .commit()
        return super.onCreateOptionsMenu(menu)
    }
}