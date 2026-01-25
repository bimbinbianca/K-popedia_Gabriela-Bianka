package com.example.k_popedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class Group(
    val name: String,
    val description: String,
    val pict: Int,
    val achievement: String,
    val listMembers: ArrayList<Member>,
    val listAlbums: ArrayList<Album>
) : Parcelable
