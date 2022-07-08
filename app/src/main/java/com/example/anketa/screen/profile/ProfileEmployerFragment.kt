package com.example.anketa.screen.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anketa.R
import com.example.anketa.data.profile.Vacancy
import com.example.anketa.databinding.FragmentProfileEmployerBinding
import com.example.anketa.screen.ViewPager2Adapter
import com.google.android.material.tabs.TabLayoutMediator

class ProfileEmployerFragment : Fragment() {

    private var _binding: FragmentProfileEmployerBinding? = null
    private val binding get() = _binding!!

    private lateinit var vacancyAdapter: VacancyAdapter
    private lateinit var imageAdapter: ViewPager2Adapter
    private var callbacks: NavBarCallbacks? = null

    val images1 = arrayOf(
        R.drawable.restaurant_one_1,
        R.drawable.restaurant_one_2,
        R.drawable.restaurant_one_3,
        R.drawable.restaurant_one_4,
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as NavBarCallbacks
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileEmployerBinding.inflate(inflater, container, false)

        return binding.apply {
            initAdapters()
            bindEditProfileClick()
        }.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bindEditProfileClick() {
        binding.btnProfileEmployerEdit.setOnClickListener {
            callbacks?.hideNavBar()
            findNavController().navigate(
                ProfileEmployerFragmentDirections.actionProfileEmployerToEditProfileEmployer(false)
            )
        }
    }

    private fun initAdapters() {
        binding.recyclerProfileEmployer.layoutManager = LinearLayoutManager(context)
        vacancyAdapter = VacancyAdapter { vacancy -> onItemClick(vacancy) }
        binding.recyclerProfileEmployer.adapter = vacancyAdapter
        imageAdapter = ViewPager2Adapter(images1)
        binding.viewpagerProfileEmployer.adapter = imageAdapter

        TabLayoutMediator(
            binding.tablayoutProfileEmployer,
            binding.viewpagerProfileEmployer
        ) { tab, position ->
            tab.view.isClickable = false
        }.attach()
    }

    private fun onItemClick(vacancy: Vacancy) {
        findNavController().navigate(
            ProfileEmployerFragmentDirections.actionProfileEmployerToVacancy(
                vacancy
            )
        )
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }
}