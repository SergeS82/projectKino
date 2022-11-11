package ru.anudx.project_kino

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.recyclerview.widget.*
import ru.anudx.project_kino.adapter.MainAdapter
import ru.anudx.project_kino.databinding.ActivityMainBinding
import ru.anudx.project_kino.decorations.RecyclerViewDecoration
import ru.anudx.project_kino.item_touch_helpers.SimpleItemTouchHelperCallback

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    private lateinit var adapter: MainAdapter
    private val KEY_MANAGER_STATE = "KeyForLayoutManagerState"
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        initNavigation()
        b.recyclerView.layoutManager = LinearLayoutManager(this)
        b.recyclerView.setHasFixedSize(true)
        //b.recyclerView.itemAnimator = RecyclerViewItemAnimator(this) //DefaultItemAnimator()
        b.recyclerView.itemAnimator = DefaultItemAnimator()
        val itemAnimator = b.recyclerView.itemAnimator
        if (itemAnimator is  DefaultItemAnimator) {
            itemAnimator.supportsChangeAnimations = false
        }
        adapter = MainAdapter(this)
        adapter.popupData.dataToBeParsed()
        b.recyclerView.adapter = adapter
        val sidePadding = resources.getDimensionPixelSize(R.dimen.sidPadding)
        val topPadding = resources.getDimensionPixelSize(R.dimen.sidPadding)
        b.recyclerView.addItemDecoration(RecyclerViewDecoration(this, sidePadding, topPadding)) // может быть несколько декороторов
        layoutManager = b.recyclerView.layoutManager ?: LinearLayoutManager(this)
        fun restoreState(){
            val outState: Parcelable?= savedInstanceState?.getParcelable(KEY_MANAGER_STATE)
            layoutManager.onRestoreInstanceState(outState)
        }
        restoreState()
        // ItemTouchHelper.Callback()
        val callBack = SimpleItemTouchHelperCallback(adapter)
        val touchHelper = ItemTouchHelper(callBack)
        touchHelper.attachToRecyclerView(b.recyclerView)
        // linearSnapHelper
        //val linearSnapHelper = LinearSnapHelper()
        //linearSnapHelper.attachToRecyclerView(b.recyclerView)
        // PagerSnapHelper
        val pageSnapHelper = PagerSnapHelper()
        pageSnapHelper.attachToRecyclerView(b.recyclerView)
        //b.recyclerView.layoutAnimation = AnimationUtils.loadLayoutAnimation(this, R.anim.recycle_lalyout_animation)
        //b.recyclerView.scheduleLayoutAnimation()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_MANAGER_STATE, layoutManager.onSaveInstanceState())
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
                    //Toast.makeText(this, R.string.btn_favorites, Toast.LENGTH_SHORT).show()
                    //adapter.popupData.addData()
                    true
                }
                R.id.menu_later -> {
                    //Toast.makeText(this, R.string.btn_later, Toast.LENGTH_SHORT).show()
                    //adapter.popupData.removeLast()
                    true
                }
                R.id.menu_library -> {
                    //Toast.makeText(this, R.string.btn_library, Toast.LENGTH_SHORT).show()
                    //adapter.popupData.updateTitle("qwerty")
                    true
                }
                else -> false
            }
        }
    }
}

