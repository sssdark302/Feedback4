package com.example.feedback4_142514.modelos

data class Novela(
    private val id: Int? = null,
    val titulo: String,
    val autor: String,
    val genero: String?,
    val paginas: Int,
    val descripcion: String,
    var esFavorita: Boolean = false
) {
    internal fun getId(): Int? = id
}
