package com.example.anketa.screen.profile.employer

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
import com.example.anketa.data.Role
import com.example.anketa.databinding.FragmentEditProfileEmployerBinding
import com.example.anketa.prefs
import com.example.anketa.screen.profile.NavBarCallbacks

class EditProfileEmployerFragment : Fragment() {

    private var _binding: FragmentEditProfileEmployerBinding? = null
    private val binding get() = _binding!!

    private val args: EditProfileEmployerFragmentArgs by navArgs()
    private var callbacks: NavBarCallbacks? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as NavBarCallbacks
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigateUp()
            if (prefs.isProfileDataSet) {
                callbacks?.showNavBar()
            }
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
//            scrollView.post { scrollView.smoothScrollTo(0, scrollView.getChildAt(0).height) }
            if (args.isLogin) {
                View.GONE.let {
                    headerStart.visibility = it
                    btnDecline.visibility = it
                    btnInstagram.layout.visibility = it
                    btnLogout.visibility = it
                    btnSave.visibility = it
                }

                View.VISIBLE.let {
                    headerCenterFillProfile.visibility = it
                    btnBack.visibility = it
                    btnContinue.visibility = it
                }

                btnBack.setOnClickListener { findNavController().navigateUp() }

                btnContinue.setOnClickListener {
                    callbacks?.showNavBar()
                    prefs.isProfileDataSet = true
                    findNavController().navigate(
                        EditProfileEmployerFragmentDirections.actionToHome()
                    )
                }

            } else {
//                scrollView.updatePadding(bottom = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._32sdp))

                View.VISIBLE.let {
                    btnLogout.visibility = it
                    btnSave.visibility = it
                }

                btnContinue.visibility = View.GONE

                btnSave.setOnClickListener {
                    callbacks?.showNavBar()
                    findNavController().navigateUp()
                }

                btnDecline.setOnClickListener {
                    callbacks?.showNavBar()
                    findNavController().navigateUp()
                }

                btnLogout.setOnClickListener {
                    prefs.isProfileDataSet = false
                    prefs.role = Role.Empty
                    findNavController().navigate(EditProfileEmployerFragmentDirections.actionToLogin())
                }
            }
        }
    }

    private fun setBottomMargin(binding: FragmentEditProfileEmployerBinding, margin: Int) {
        val layoutParams = binding.mainLayout.layoutParams as FrameLayout.LayoutParams
        layoutParams.bottomMargin = margin
    }

}