package ru.anudx.project_kino

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.forEach
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior
import com.google.android.material.snackbar.Snackbar
import ru.anudx.project_kino.adapters.CommonAdapter
import ru.anudx.project_kino.adapters.DelegateDescriptionAdapter
import ru.anudx.project_kino.databinding.ActivityMainBinding
import ru.anudx.project_kino.decorations.RecyclerDecoration
import ru.anudx.project_kino.item_touch_helper.MainItemTouchHelper
import timber.log.Timber
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    private var timeBackPressed = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(App.lifeCycleListener)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        OrientationInit()
        initNavigation()
        initRecyclerView()
    }
    companion object {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)
        const val TIME_INTERVAL_2S = 2000
    }

    private fun OrientationInit() {
        when (resources.configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                val leftFragmentTag = "left_fragment_in"
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.left_fragment, TestFragment(), leftFragmentTag)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .addToBackStack(null)
                    .commitAllowingStateLoss()
                val rightFragmentTag = "right_fragment_in"
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.right_fragment, TestFragment2(), rightFragmentTag)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    private fun initRecyclerView() {
        val adapter = CommonAdapter(this)
        adapter.dataManager.init()
        with(b.filmsRecycler) {
            layoutManager = TestLinearLayoutManager(this@MainActivity);
            itemAnimator = DefaultItemAnimator()
            recycledViewPool.setMaxRecycledViews(R.layout.films_item, 1)
            val snackbarScrollToFirst = Snackbar.make(b.root, "", Snackbar.LENGTH_SHORT)
            b.nestedScroll.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                val i = v.height * Resources.getSystem().displayMetrics.density
            } // TODO: надо как-то использовать для вычисления окнца прокрутки

            val action =
                snackbarScrollToFirst.setAction(context.getString(R.string.goto_list_top)) {
                    val i = b.nestedScroll.isSmoothScrollingEnabled
                    b.nestedScroll.smoothScrollBy(0, -b.nestedScroll.scrollY)
                    val params = b.bottomMenu.layoutParams as CoordinatorLayout.LayoutParams
                    val behavior = params.behavior as HideBottomViewOnScrollBehavior
                    behavior.slideUp(b.bottomMenu)
                }
            val test = object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    val lastVisible =
                        (layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                    val total = (layoutManager as RecyclerView.LayoutManager).itemCount
                    if (lastVisible >= total - 2 && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        snackbarScrollToFirst.show()
                        //Toast.makeText(this@MainActivity, "qwerty", Toast.LENGTH_SHORT).show()
                    }
                    super.onScrollStateChanged(recyclerView, newState)
                }
            }
            addOnScrollListener(test)
            setItemViewCacheSize(1)
            addItemDecoration(
                RecyclerDecoration(
                    this@MainActivity,
                    resources.getDimensionPixelSize(R.dimen.side_padding),
                    resources.getDimensionPixelSize(R.dimen.top_padding)
                )
            )
            this.adapter = adapter
            val touchHelper = ItemTouchHelper(MainItemTouchHelper(adapter))
            touchHelper.attachToRecyclerView(this)

        }
    }

    override fun onStart() {
        super.onStart()
        Timber.tag("debug_log").d("onStart")
    }

    override fun onResume() {
        super.onResume()
        Timber.tag("debug_log").d("onResume")
    }

    override fun onPause() {
        super.onPause()
        Timber.tag("debug_log").d("onPause")
    }

    override fun onStop() {
        super.onStop()
        Timber.tag("debug_log").d("onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.tag("debug_log").d("onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.tag("debug_log").d("onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        b.filmsRecycler.forEach {view ->
            val holder = b.filmsRecycler.getChildViewHolder(view)
            when (holder) {
                is DelegateDescriptionAdapter.ViewHolder -> {
                    outState.putString("description_item_"+holder.id, holder.description.text.toString())
                }
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        b.filmsRecycler.forEach { view ->
            val holder = b.filmsRecycler.getChildViewHolder(view)
            when (holder) {
                is DelegateDescriptionAdapter.ViewHolder -> {
                    holder.description.text = savedInstanceState.getString("description_item_"+holder.id) ?: ""
                }
            }
        }

    }

    private fun initNavigation() {
        b.toolBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.Settings -> {
                    Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
        b.bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_favorites -> {
                    DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        val time = "$year/${month+1}/$dayOfMonth"
                        b.toolBar.title = time
                    }, currentYear, currentMonth, currentHour).show()
                    true
                }
                R.id.menu_later -> {
                    calendar
                    TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hour, minute ->
                        val time = "$hour : $minute"
                        b.toolBar.title = time
                    }
                        , calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),true).show()
                    true
                }
                R.id.menu_library -> {
                    DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        val date = "$year/${month+1}/$dayOfMonth"
                        TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hour, minute ->
                            val time = "$date $hour : $minute"
                            b.toolBar.title = time
                        }
                            , calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),true).show()
                    }, currentYear, currentMonth, currentHour).show()
                    true
                }
                else -> false
            }
        }
    }
    fun passData(editext: String) {
        val bundle = Bundle()
        bundle.putString("2fragment2", editext)
        val frag2 = TestFragment2()
        frag2.arguments = bundle
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.right_fragment, frag2)
            .addToBackStack(null)
            .commit()
    }
    fun startSecondFragment(img: ImageView){
        supportFragmentManager
            .beginTransaction()
            .addSharedElement(img,"transition1")
            .addToBackStack(null)
            .replace(R.id.left_fragment,TestFragment3())
            .commit()
    }

    override fun onBackPressed() {
        if (timeBackPressed+ TIME_INTERVAL_2S > System.currentTimeMillis()){
            super.onBackPressed()
            finish()
        }else{
            Toast.makeText(this, R.string.double_press_for_exit, Toast.LENGTH_SHORT).show()
        }
        timeBackPressed = System.currentTimeMillis()
    }
}