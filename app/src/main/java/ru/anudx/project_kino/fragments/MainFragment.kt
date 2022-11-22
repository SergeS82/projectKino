package ru.anudx.project_kino.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior
import ru.anudx.project_kino.App
import ru.anudx.project_kino.DetailActivity
import ru.anudx.project_kino.R
import ru.anudx.project_kino.adapters.CommonAdapter
import ru.anudx.project_kino.databinding.FragmentMainBinding
import ru.anudx.project_kino.decorations.RecyclerDecoration
import ru.anudx.project_kino.item_touch_helper.MainItemTouchHelper

class MainFragment() : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    val b: FragmentMainBinding by lazy {
        FragmentMainBinding.bind(this.requireView())
    }
    private val mainContext = App.mainContext

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onStart() {
        super.onStart()
        initRecyclerView()
        initNavigation()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
            }
    }
    private fun initRecyclerView() {
        val adapter = CommonAdapter()
        adapter.dataManager.init()
        with(b.filmsRecycler) {
            layoutManager = TestLinearLayoutManager(mainContext);
            itemAnimator = DefaultItemAnimator()
            recycledViewPool.setMaxRecycledViews(ru.anudx.project_kino.R.layout.films_item, 1)
            val snackbarScrollToFirst = com.google.android.material.snackbar.Snackbar.make(this, "", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT)
            b.nestedScroll.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                val i = v.height * android.content.res.Resources.getSystem().displayMetrics.density
            } // TODO: надо как-то использовать для вычисления окнца прокрутки

            val action =
                snackbarScrollToFirst.setAction(mainContext.getString(R.string.goto_list_top)) {
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
                    if (lastVisible >= total - 2 && newState == androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE) {
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
                    mainContext,
                    mainContext.resources.getDimensionPixelSize(R.dimen.side_padding),
                    mainContext.resources.getDimensionPixelSize(R.dimen.top_padding)
                )
            )
            this@with.adapter = adapter
            val touchHelper = ItemTouchHelper(MainItemTouchHelper(adapter))
            touchHelper.attachToRecyclerView(this)
        }
    }
    fun initNavigation(){
        b.bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_favorites -> {
                    val intent = Intent(mainContext, DetailActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> true
            }
        }
    }

}