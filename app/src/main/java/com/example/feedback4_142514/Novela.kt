package com.example.feedback4_142514

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Novela(
    var id: Int,
    var titulo: String,
    var autor: String,
    var genero: String,
    var paginas: Int,
    var descripcion: String
): Parcelable
