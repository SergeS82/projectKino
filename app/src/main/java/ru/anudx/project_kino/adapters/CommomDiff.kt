package ru.anudx.project_kino.adapters

import androidx.recyclerview.widget.DiffUtil
import ru.anudx.project_kino.model.InterfaceData

class CommomDiff(val oldList: ArrayList<InterfaceData>, val newList: ArrayList<InterfaceData>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].lId == newList[newItemPosition].lId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}