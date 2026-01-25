package com.example.k_popedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Album(
    val pictAlbum : Int,
    val nameAlbum : String,
    val yearAlbum : String
) : Parcelable


