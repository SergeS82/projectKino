package ru.anudx.project_kino.item_touch_helper

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.anudx.project_kino.adapters.FilmsAdapter
import java.util.*
import kotlin.collections.ArrayList

class MainItemTouchHelper(val adapter: FilmsAdapter): ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags,swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val items = ArrayList(adapter.data)
        val fromPosition = viewHolder.adapterPosition
        val toPosition = target.adapterPosition
        if (fromPosition<toPosition) {
            for (i in fromPosition until toPosition)
                Collections.swap(items, i, i + 1)
        }
        else {
            for (i in fromPosition downTo toPosition+1)
                Collections.swap(items, i, i - 1)
        }
        adapter.data = items
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.dataManager.removeSwiped(viewHolder.adapterPosition)
    }
}