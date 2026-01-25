package com.example.k_popedia

import android.R
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Member(
    val nameMember: String,
    val pictMember: Int,
    val birthdayMember: String
) : Parcelable
