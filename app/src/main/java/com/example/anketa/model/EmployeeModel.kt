package com.example.anketa.model

import androidx.annotation.ColorInt

data class EmployeeModel(
    val name: String,
    val age: Int,
    val description: String,
    @ColorInt val backgroundColor: Int
)