package ru.anudx.project_kino.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.anudx.project_kino.model.FilmsModel

class FilmsDiffUtil(val oldList: ArrayList<FilmsModel>, val newList: ArrayList<FilmsModel>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList[oldItemPosition] == newList[newItemPosition]
}