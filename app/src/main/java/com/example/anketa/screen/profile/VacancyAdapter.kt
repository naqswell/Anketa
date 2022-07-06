package com.example.anketa.screen.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.anketa.R
import com.example.anketa.data.profile.Vacancy
import com.example.anketa.databinding.VacancyCardBinding

class VacancyAdapter(private val onItemClicked: (Vacancy) -> Unit) :
    RecyclerView.Adapter<VacancyViewHolder>() {

    var vacancyList = mutableListOf(
        Vacancy("Бармен", "60 000 - 70 000 руб", "3 года", "Нам нужен быстрый и ловкий бармен, который спсосбен понимать речь клиентов на фоне громокй музыки"),
        Vacancy("Официант", "50 000 - 60 000 руб", "1 год", "В поисках чуткого человека с хорошей памятью и координацией."),
        Vacancy("Хостес", "70 000 - 75 000 руб", "3 года", "Нам нужен человек с высоким чувством эмпатии, любящий людей и умеющий подстроиться под клиента. Знание английского языка обязательно."),
        Vacancy("Клининг персонал", "30 000 - 35 000 руб", "Без опыта", "В поисках человека, который готов поддерживать чистоту во всем заведении."),
    )
        private set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = VacancyCardBinding.inflate(inflater, parent, false)
        return VacancyViewHolder(binding) { value -> onItemClicked(value) }
    }

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        val vacancy = vacancyList[position]

        with(holder.binding) {
            vacancyPosition.text = vacancy.position
            vacancySalary.text = vacancy.salary
            vacancyExperience.text = vacancy.experience
            vacancyDetail.text = vacancy.detail
        }
        holder.setItem(vacancy)
    }

    override fun getItemCount(): Int {
        return vacancyList.size
    }
}

class VacancyViewHolder(val binding: VacancyCardBinding, val onItemClicked: (Vacancy) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    fun setItem(vacancy: Vacancy) {
        val editBtn: Button = itemView.findViewById(R.id.btn_vacancy_edit)
        editBtn.setOnClickListener {
            onItemClicked(vacancy)
        }
    }
}