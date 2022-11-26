package ru.anudx.project_kino

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import ru.anudx.project_kino.databinding.ActivityMainBinding
import ru.anudx.project_kino.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var b: ActivityMainBinding
    private var timeBackPressed = 0L
    companion object{
        val TIME_INTERVAL_2S = 2000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        App.mainContext = this
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_layout, MainFragment(), resources.getString(R.string.main_fragment_tag))
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
        }else{
            super.onBackPressed()
        }
    }
}