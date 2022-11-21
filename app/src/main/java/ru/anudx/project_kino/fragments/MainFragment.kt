package ru.anudx.project_kino.fragments

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
import ru.anudx.project_kino.R
import ru.anudx.project_kino.adapters.CommonAdapter
import ru.anudx.project_kino.databinding.FragmentMainBinding
import ru.anudx.project_kino.decorations.RecyclerDecoration
import ru.anudx.project_kino.item_touch_helper.MainItemTouchHelper

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment() : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var b: FragmentMainBinding
    private val mainContext = App.mainContext

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        b = FragmentMainBinding.bind(view)
        initRecyclerView()
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
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

}