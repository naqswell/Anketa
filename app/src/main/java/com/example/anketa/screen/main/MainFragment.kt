package com.example.anketa.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.anketa.R
import com.example.anketa.databinding.FragmentMainBinding
import com.example.anketa.model.EmployeeTwoCardModel
import com.example.anketa.screen.ViewPager2Adapter
import com.example.anketa.viewmodel.EmployeeViewModel
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    val viewModel: EmployeeViewModel by activityViewModels()
    private lateinit var adapterCardOne: ViewPager2Adapter
    private lateinit var adapterCardTwo: ViewPager2Adapter

    val images1 = arrayOf(
        R.drawable.person_one_1,
        R.drawable.person_one_2,
        R.drawable.person_one_3
    )

    val images2 = arrayOf(
        R.drawable.restaurant_one_1,
        R.drawable.restaurant_one_2,
        R.drawable.restaurant_one_3,
        R.drawable.restaurant_one_4,
    )

    val images3 = arrayOf(
        R.drawable.person_three_1,
        R.drawable.person_three_2,
        R.drawable.person_three_3,
    )

    val arrayOfImgArray = arrayOf(images1, images2, images3)

    private var initFlag = false

    private var counter = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.apply {
            initUI(this)
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel
            .stream
            .observe(viewLifecycleOwner) {
                bindCard(it)
                initFlag = !initFlag
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initUI(binding: FragmentMainBinding) {
        with(binding) {
            motionLayout.setTransitionListener(object : TransitionAdapter() {
                override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                    when (currentId) {
                        R.id.offScreenUnlike, R.id.offScreenLike -> {
                            motionLayout.progress = 0f
                            motionLayout.setTransition(R.id.startMain, R.id.startMain)
                            viewModel.swipe()
                        }
                    }
                }
            })

            adapterCardOne = ViewPager2Adapter(images1)
            viewpager.adapter = adapterCardOne

            TabLayoutMediator(tablayout, viewpager) { tab, position ->
                tab.view.isClickable = false;
            }.attach()

            adapterCardTwo = ViewPager2Adapter(images2)
            viewpager2.adapter = adapterCardTwo

            btnLike.setOnClickListener {
                motionLayout.transitionToState(R.id.offScreenLike)
            }

            btnDislike.setOnClickListener {
                motionLayout.transitionToState(R.id.offScreenUnlike)
            }

            viewModel.swipe()
        }
    }

    private fun bindCard(model: EmployeeTwoCardModel) {
        with(binding) {
//            containerCardTwo.setBackgroundColor(model.cardBottom.backgroundColor)
//            containerCardOne.setBackgroundColor(model.cardTop.backgroundColor)
            viewpager.setCurrentItem(0, false)
//            if (initFlag) {
//                viewPagerAdapter.updateImages(images2)
//            } else {
            val idx = counter % (arrayOfImgArray.size)
            val idx2 = (counter + 1) % (arrayOfImgArray.size)
            adapterCardOne.updateImages(arrayOfImgArray[idx])
            adapterCardTwo.updateImages(arrayOfImgArray[idx2])
            counter += 1
//            }
        }
    }
}