package com.example.anketa.screen.profile

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.anketa.R
import com.example.anketa.databinding.FragmentEditProfileEmployeeBinding

class EditProfileEmployeeFragment : Fragment() {

    private var _binding: FragmentEditProfileEmployeeBinding? = null
    private val binding get() = _binding!!

    private val args: EditProfileEmployeeFragmentArgs by navArgs()
    private var callbacks: NavBarCallbacks? = null
    private lateinit var navigationBack: OnBackPressedCallback

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as NavBarCallbacks
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavigationBack()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditProfileEmployeeBinding.inflate(inflater, container, false)

        return binding.apply {
            initUI(this)
        }.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private fun initUI(binding: FragmentEditProfileEmployeeBinding) {
        with(binding) {
            if (args.isLogin) {
                callbacks?.hideNavBar()
                setBottomMargin(
                    binding,
                    resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._16sdp)
                )
                headerStart.visibility = View.GONE
                btnDecline.visibility = View.GONE
                headerCenterFillProfile.visibility = View.VISIBLE
                btnBack.visibility = View.VISIBLE

                btnBack.setOnClickListener { findNavController().navigateUp() }

                btnContinue.apply {
                    visibility = View.VISIBLE
                    setOnClickListener {
                        callbacks?.showNavBar()
                        findNavController().navigate(
                            EditProfileEmployeeFragmentDirections.actionToHome()
                        )
                    }
                }
            } else {
                val actionBarHeight = with(TypedValue().also {
                    requireContext().theme.resolveAttribute(
                        android.R.attr.actionBarSize,
                        it,
                        true
                    )
                }) {
                    TypedValue.complexToDimensionPixelSize(this.data, resources.displayMetrics)
                }
                setBottomMargin(binding, actionBarHeight)
                headerStart.visibility = View.GONE
                btnDecline.visibility = View.GONE
                headerCenterYourProfile.visibility = View.VISIBLE
                btnBack.visibility = View.VISIBLE

                btnBack.setOnClickListener {
                    callbacks?.showNavBar()
                    requireActivity().onBackPressed()
                }
            }
        }
    }

    private fun setBottomMargin(binding: FragmentEditProfileEmployeeBinding, margin: Int) {
        val layoutParams = binding.mainLayout.layoutParams as FrameLayout.LayoutParams
        layoutParams.bottomMargin = margin
    }

    private fun initNavigationBack() {
        navigationBack = if (args.isLogin) {
            requireActivity().onBackPressedDispatcher.addCallback(this) {
                findNavController().navigateUp()
                callbacks?.showNavBar()
            }
        } else {
            requireActivity().onBackPressedDispatcher.addCallback(this) {
                findNavController().navigate(R.id.navigation_main)
                callbacks?.showNavBar()
            }
        }
    }

}