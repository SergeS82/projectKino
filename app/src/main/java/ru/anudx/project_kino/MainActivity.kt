package ru.anudx.project_kino

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ru.anudx.project_kino.adapters.CommonAdapter
import ru.anudx.project_kino.adapters.DelegateFilmsAdapter
import ru.anudx.project_kino.databinding.ActivityMainBinding
import ru.anudx.project_kino.decorations.RecyclerDecoration
import ru.anudx.project_kino.item_touch_helper.MainItemTouchHelper

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        initNavigation()
        val adapter = CommonAdapter(this)
        adapter.dataManager.init()

        with(b.filmsRecycler) {
            layoutManager = TestLinearLayoutManager(this@MainActivity);
            itemAnimator = DefaultItemAnimator()
            recycledViewPool.setMaxRecycledViews(R.layout.films_item, 1)
            val snackbarScrollToFirst = Snackbar.make(b.root, "", Snackbar.LENGTH_SHORT)
            b.nestedScroll.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                val i =  v.height * Resources.getSystem().displayMetrics.density
            } // TODO: надо как-то использовать для вычисления окнца прокрутки 

            snackbarScrollToFirst.setAction("Вернуться к началу списка"){
                val i = b.nestedScroll.isSmoothScrollingEnabled
                b.nestedScroll.smoothScrollBy(0, -b.nestedScroll.scrollY)
            }
            val test = object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    val lastVisible = (layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                    val total = (layoutManager as RecyclerView.LayoutManager).itemCount
                    if (lastVisible >= total-2 && newState == RecyclerView.SCROLL_STATE_IDLE){
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
            val touchHelper = ItemTouchHelper(MainItemTouchHelper( adapter))
            touchHelper.attachToRecyclerView(this)

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
                    Toast.makeText(this, R.string.btn_favorites, Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.menu_later -> {
                    Toast.makeText(this, R.string.btn_later, Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.menu_library -> {
                    Toast.makeText(this, R.string.btn_library, Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}