package ru.anudx.project_kino

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import ru.anudx.project_kino.databinding.ActivityMainFragmentBinding

class MainActivityFragment : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
private lateinit var b: ActivityMainFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainFragmentBinding.inflate(layoutInflater)
        setContentView(b.root)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_layout, MainFragment(this), resources.getString(R.string.main_fragment_tag))
            .commit()
    }

}