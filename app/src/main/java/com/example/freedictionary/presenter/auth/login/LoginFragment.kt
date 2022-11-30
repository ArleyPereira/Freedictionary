package com.example.freedictionary.presenter.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.freedictionary.R
import com.example.freedictionary.databinding.FragmentLoginBinding
import com.example.freedictionary.util.BaseFragment
import com.example.freedictionary.util.FirebaseHelper
import com.example.freedictionary.util.StateView
import com.example.freedictionary.util.showBottomSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    private fun initListeners() {
        binding.textRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.textRecover.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_recoverFragment)
        }

        binding.btnLogin.setOnClickListener { validateData() }
    }

    private fun validateData() {
        val email = binding.editEmail.text.toString().trim()
        val password = binding.editPassword.text.toString().trim()

        if (email.isNotEmpty()) {
            if (password.isNotEmpty()) {
                hideKeyboard()

                loginUser(email, password)
            } else {
                showBottomSheet(message = getString(R.string.text_password_empty))
            }
        } else {
            showBottomSheet(message = getString(R.string.text_email_empty))
        }
    }

    private fun loginUser(email: String, password: String) {
        loginViewModel.login(email, password).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    binding.progressBar.isVisible = true
                }

                is StateView.Success -> {
                    val navOptions: NavOptions =
                        NavOptions.Builder().setPopUpTo(R.id.loginFragment, true).build()
                    findNavController().navigate(R.id.action_global_homeFragment, null, navOptions)
                }

                is StateView.Error -> {
                    binding.progressBar.isVisible = false
                    showBottomSheet(
                        message = getString(
                            FirebaseHelper.validError(
                                stateView.message ?: ""
                            )
                        )
                    )
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}