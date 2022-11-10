package ru.anudx.project_kino

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.anudx.project_kino.adapter.MainAdapter
import java.util.*

class SimpleItemTouchHelperCallback(val adapter: MainAdapter) : ItemTouchHelper.Callback(){
    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        //Настраиваем флаги для drag & drop и swipe жестов
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val items = adapter.items
        val fromPosition = viewHolder.adapterPosition
        val toPosition = target.adapterPosition
        //Меняем элементы местами с помощью метода swap
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(items, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(items, i, i - 1)
            }
        }
        //Сообщаем об изменениях адаптеру
        //Or DiffUtil
        adapter.notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        //Удаляем элемент из списка после жеста swipe
        adapter.popupData.removeSwiped(viewHolder.adapterPosition)
        //Or DiffUtil
        adapter.notifyItemRemoved(viewHolder.adapterPosition);
    }


}