package com.example.anketa.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.anketa.R
import com.example.anketa.databinding.FragmentMainBinding
import com.example.anketa.model.EmployeeModel
import com.example.anketa.model.EmployeeTwoCardModel
import com.example.anketa.screen.ViewPager2Adapter
import com.example.anketa.viewmodel.EmployeeViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.*

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    val viewModel: EmployeeViewModel by activityViewModels()
    private lateinit var adapterCardOne: ViewPager2Adapter
//    private lateinit var adapterCardTwo: ViewPager2Adapter

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private var initFlag = false

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
                bindCards(it)
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
                        R.id.offScreenUnlike -> {
                            motionLayout.progress = 0f
                            motionLayout.setTransition(R.id.startMain, R.id.offScreenUnlike)
                            viewModel.swipe()
                        }
                        R.id.offScreenLike -> {
                            motionLayout.progress = 0f
                            motionLayout.setTransition(R.id.startMain, R.id.offScreenUnlike)
                            viewModel.swipe()
                        }
                    }
                }
            })

            adapterCardOne = ViewPager2Adapter(viewModel.topCard.arrayOfImg)
            viewpager.adapter = adapterCardOne
            TabLayoutMediator(tablayout, viewpager) { tab, position ->
                tab.view.isClickable = false;
            }.attach()
            bindTopCard(viewModel.topCard)
            bindBottomCard(viewModel.bottomCard)

            btnLike.setOnClickListener {
                motionLayout.transitionToState(R.id.offScreenLike)
            }

            btnDislike.setOnClickListener {
                motionLayout.transitionToState(R.id.offScreenUnlike)
            }
        }
    }

    private fun updateAdapterBeforeSwipe(model: EmployeeTwoCardModel) {
        with(binding) {
            viewpager.setCurrentItem(0, false)
            adapterCardOne.updateImages(model.cardTop.arrayOfImg)
            bindTopCard(model.cardTop)
        }
    }

    private fun bindTopCard(cardTop: EmployeeModel) {
        with(binding) {
            titlePosition.text = cardTop.position
            titleName.text = cardTop.name
            dataCity.text = cardTop.city
            dataSalary.text = cardTop.salary
            dataExperience.text = cardTop.experience
            dataReviews.text = cardTop.reviews.toString()
            txtRatingProfile.text = cardTop.rating.toString()
        }

    }

    private fun bindBottomCard(cardBottom: EmployeeModel) {
        with(binding) {
            imageCardTwo.setImageDrawable(ResourcesCompat.getDrawable(resources, cardBottom.arrayOfImg[0], null))
            titlePosition2.text = cardBottom.position
            titleName2.text = cardBottom.name
            dataCity2.text = cardBottom.city
            dataSalary2.text = cardBottom.salary
            dataExperience2.text = cardBottom.experience
            dataReviews2.text = cardBottom.reviews.toString()
            txtRatingProfile2.text = cardBottom.rating.toString()
        }
    }

    private fun bindCards(model: EmployeeTwoCardModel) {
        coroutineScope.launch {
            updateAdapterBeforeSwipe(model)
            delay(10)
            bindBottomCard(model.cardBottom)
        }
    }
}