package ru.anudx.project_kino.adapters

import androidx.recyclerview.widget.DiffUtil
import ru.anudx.project_kino.model.CommonData
import ru.anudx.project_kino.model.FilmsData

class CommomDiff(val oldList: ArrayList<CommonData>, val newList: ArrayList<CommonData>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}