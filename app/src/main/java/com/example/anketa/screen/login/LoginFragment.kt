package com.example.anketa.screen.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.anketa.data.Role
import com.example.anketa.databinding.FragmentLoginBinding
import com.example.anketa.prefs

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.apply {

            val actionToEmployer = LoginFragmentDirections.actionLoginToEditProfileEmployer(true)
            with(binding) {
                btnEmployer.setOnClickListener {
                    prefs.role = Role.Employer
                    findNavController().navigate(actionToEmployer)
                }

                val actionToEmployee = LoginFragmentDirections.actionLoginToEditProfileEmployee(true)
                btnEmployee.setOnClickListener {
                    prefs.role = Role.Employee
                    findNavController().navigate(actionToEmployee)
                }
            }
        }.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}