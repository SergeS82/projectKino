package ru.anudx.project_kino.model

data class FilmsModel(val title: String, val description: String, val img: Int, override val id: String) : Item {
}