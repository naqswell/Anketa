package com.example.anketa.screen.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.anketa.databinding.FragmentEditProfileEmployerBinding

class EditProfileEmployerFragment : Fragment() {

    private var _binding: FragmentEditProfileEmployerBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEditProfileEmployerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnEditProfileEmployerBack.setOnClickListener { findNavController().navigateUp() }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}