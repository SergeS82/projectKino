package ru.anudx.project_kino.adapters

import ru.anudx.project_kino.model.InterfaceData

interface InterfaceViewHolder {
    var id:Int
    fun bind(item: InterfaceData)
}