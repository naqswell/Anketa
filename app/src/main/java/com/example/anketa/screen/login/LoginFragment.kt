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

    interface Callbacks {
        fun onRoleSet()
    }

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!
    private lateinit var callbacks: Callbacks


    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        val action = LoginFragmentDirections.actionLoginToHome()

        with(binding) {
            btnEmployer.setOnClickListener {
                prefs.role = Role.Employer
                callbacks.onRoleSet()
                findNavController().navigate(action)
            }

            btnEmployee.setOnClickListener {
                prefs.role = Role.Employee
                callbacks.onRoleSet()
                findNavController().navigate(action)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}