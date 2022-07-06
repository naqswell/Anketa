package com.example.anketa.data.profile

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Vacancy(
    val position: String,
    val salary: String,
    val experience: String,
    val detail: String
): Parcelable