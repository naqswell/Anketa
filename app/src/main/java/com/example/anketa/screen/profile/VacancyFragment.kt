package com.example.anketa.screen.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.anketa.R
import com.example.anketa.databinding.FragmentVacancyBinding

class VacancyFragment : Fragment() {

    private var _binding: FragmentVacancyBinding? = null
    private val binding get() = _binding!!

    private val args: VacancyFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVacancyBinding.inflate(inflater, container, false)

        return binding.apply {
            val vacancy = args.vacancy
            vacancyPosition.editTxt.setText(vacancy.position)
            vacancyPosition.editTxtTitle.text = getString(R.string.vacancy_edit_txt_title)

            vacancySalary.editTxt.setText(vacancy.salary)
            vacancySalary.editTxtTitle.text = getString(R.string.salary_edit_txt_title)

            vacancyExperience.editTxt.setText(vacancy.experience)
            vacancyExperience.editTxtTitle.text = getString(R.string.experience_edit_txt_title)

            vacancyAbout.editTxt.setText(vacancy.detail)
            vacancyAbout.editTxtTitle.text = getString(R.string.detail_edit_txt_title)
        }.root
    }
}