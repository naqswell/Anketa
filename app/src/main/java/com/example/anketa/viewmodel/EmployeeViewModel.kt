package com.example.anketa.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anketa.R
import com.example.anketa.model.EmployeeModel
import com.example.anketa.model.EmployeeTwoCardModel

class EmployeeViewModel : ViewModel() {

    val images1 = arrayOf(
        R.drawable.person_one_1,
        R.drawable.person_one_2,
        R.drawable.person_one_3,
    )

    val images2 = arrayOf(
        R.drawable.person_two_1,
        R.drawable.person_two_3,
        R.drawable.person_two_2,
        )

    val images3 = arrayOf(
        R.drawable.person_three_1,
        R.drawable.person_three_2,
        R.drawable.person_three_3,
    )


    val employees = listOf(
        EmployeeModel(
            position = "Официант",
            name = "Оксана Васильева",
            city = "Санкт-Петербург",
            salary = "70 000 - 90 000 руб",
            experience = "более 5-ти лет",
            reviews = 12,
            rating = 4.9F,
            images1,
        ),
        EmployeeModel(
            position = "Повар",
            name = "Виктор Кузнецов",
            city = "Санкт-Петербург",
            salary = "120 000 - 150 000 руб",
            experience = "более 5-ти лет",
            reviews = 21,
            rating = 4.1F,
            images2,
        ),
        EmployeeModel(
            position = "Администратор",
            name = "Марина Смирнова",
            city = "Санкт-Петербург",
            salary = "70 000 - 100 000 руб",
            experience = "4 года",
            reviews = 12,
            rating = 5.0F,
            images3,
        ),
    )

    var stream = MutableLiveData<EmployeeTwoCardModel>().apply { EmployeeTwoCardModel(topCard, bottomCard) }
        private set

    private var currentIndex = 0

    val topCard
        get() = employees[currentIndex % employees.size]

    val bottomCard
        get() = employees[(currentIndex + 1) % employees.size]

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