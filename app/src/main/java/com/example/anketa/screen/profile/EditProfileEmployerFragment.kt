package com.example.anketa.screen.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.anketa.databinding.FragmentEditProfileEmployerBinding

class EditProfileEmployerFragment : Fragment() {

    private var _binding: FragmentEditProfileEmployerBinding? = null
    private val binding get() = _binding!!

    private val args: EditProfileEmployeeFragmentArgs by navArgs()
    private var callbacks: NavBarCallbacks? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as NavBarCallbacks
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigateUp()
            callbacks?.showNavBar()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditProfileEmployerBinding.inflate(inflater, container, false)

        return binding.apply {
            callbacks?.hideNavBar()
            setBottomMargin(binding, resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._16sdp))
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

    private fun initUI(binding: FragmentEditProfileEmployerBinding) {
        with(binding) {
            if (args.isLogin) {
                headerStart.visibility = View.GONE
                btnDecline.visibility = View.GONE
                headerCenter.visibility = View.VISIBLE
                btnBack.visibility = View.VISIBLE

                btnBack.setOnClickListener { findNavController().navigateUp() }

                btnContinue.apply {
                    visibility = View.VISIBLE
                    setOnClickListener {
                        callbacks?.showNavBar()
                        findNavController().navigate(
                            EditProfileEmployerFragmentDirections.actionToHome()
                        )
                    }
                }
            } else {
                btnDecline.setOnClickListener {
                    callbacks?.showNavBar()
                    findNavController().navigateUp()
                }
            }
        }
    }

    private fun setBottomMargin(binding: FragmentEditProfileEmployerBinding, margin: Int) {
        val layoutParams = binding.mainLayout.layoutParams as FrameLayout.LayoutParams
        layoutParams.bottomMargin = margin
    }

}