package com.example.anketa.screen.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.anketa.databinding.FragmentVacancyBinding

class VacancyFragment : Fragment() {

    private var binding: FragmentVacancyBinding? = null
    private val args: VacancyFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVacancyBinding.inflate(inflater, container, false)
        return binding.apply {
            val vacancy = args.vacancy
            Toast.makeText(requireContext(), vacancy.position, Toast.LENGTH_SHORT).show()
            binding!!.vacancyPosition.editTxt.setText("Lol")
        }?.root
    }
}