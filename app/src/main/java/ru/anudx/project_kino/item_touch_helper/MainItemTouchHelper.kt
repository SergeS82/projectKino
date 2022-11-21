package ru.anudx.project_kino.item_touch_helper

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.anudx.project_kino.adapters.CommonAdapter
import ru.anudx.project_kino.adapters.DelegateFilmsAdapter
import java.util.*
import kotlin.collections.ArrayList

class MainItemTouchHelper(val adapter: CommonAdapter): ItemTouchHelper.Callback() {
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
        viewHolder.itemView.animate().scaleY(1.1f).scaleX(1.1f).setDuration(10).start()
        val items = ArrayList(adapter.items)
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
        adapter.setItems(items)
        return true
    }

    override fun isLongPressDragEnabled(): Boolean = true

    override fun isItemViewSwipeEnabled(): Boolean = true


    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.dataManager.removeSwiped(viewHolder.adapterPosition)
    }
}