package com.example.anketa.viewmodel

import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anketa.model.EmployeeModel
import com.example.anketa.model.EmployeeTwoCardModel

class EmployeeViewModel: ViewModel() {

    private val data = listOf(
        EmployeeModel(
            name = "Leha", age = 23, description = "Project Manager", Color.parseColor("#c50e29")
        ),
        EmployeeModel(
            name = "Nikita Frolov", age = 22, description = "IOS Developer", Color.parseColor("#c60055")
        ),
        EmployeeModel(
            name = "Yulya", age = 22, description = "UX/UI Designer", Color.parseColor("#aa00c7")
        ),
        EmployeeModel(
            name = "Nikita Ivanov", age = 21, description = "Android Developer", Color.parseColor("#3f1dcb")
        )
    )

    var stream = MutableLiveData<EmployeeTwoCardModel>().apply { EmployeeTwoCardModel(topCard, bottomCard) }
        private set

    private var currentIndex = 0

    private val topCard
        get() = data[currentIndex % data.size]

    private val bottomCard
        get() = data[(currentIndex + 1) % data.size]

    private fun updateCards() {
        stream.value = EmployeeTwoCardModel(
            cardTop = topCard,
            cardBottom = bottomCard
        )
    }

    fun swipe() {
        currentIndex += 1
        updateCards()
    }
}