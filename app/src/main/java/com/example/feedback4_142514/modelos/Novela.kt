package com.example.feedback4_142514.modelos

data class Novela(
    private val id: Int? = null,       // id sigue siendo privado
    val titulo: String,
    val autor: String,
    val genero: String?,
    val paginas: Int,
    val descripcion: String,
    var esFavorita: Boolean = false    // Propiedad para marcar como favorita
) {
    internal fun getId(): Int? = id
}
