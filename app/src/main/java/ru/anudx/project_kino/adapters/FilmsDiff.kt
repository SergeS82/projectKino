package ru.anudx.project_kino.adapters

import androidx.recyclerview.widget.DiffUtil
import ru.anudx.project_kino.model.FilmsData

class FilmsDiff(val oldList: ArrayList<FilmsData>, val newList: ArrayList<FilmsData>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}