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
import ru.anudx.project_kino.study.StudyClipBoardFragment
import ru.anudx.project_kino.study.StudyUserInputFragment

@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity() {
    val list = mutableListOf<String>()
    lateinit var menu: Menu
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
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, MainFragment(), resources.getString(R.string.main_fragment_tag))
            .commit()
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
        } else {
            super.onBackPressed()
        }
    }
}