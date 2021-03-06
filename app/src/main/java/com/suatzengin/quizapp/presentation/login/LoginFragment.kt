package com.suatzengin.quizapp.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.suatzengin.quizapp.common.SharedPref
import com.suatzengin.quizapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isLoggedIn = SharedPref.isLoggedIn(requireActivity())

        if (isLoggedIn) {
            actionLoginToHome()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignUp.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginToRegister()
            findNavController().navigate(action)
        }
        binding.btnSignIn.setOnClickListener {
            login()
        }
        observerData()
    }

    private fun login() {
        val mail = binding.inputLayoutMail.editText?.text
        val password = binding.inputLayoutPassword.editText?.text

        if (mail != null && password != null) {
            if (mail.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Bo?? olamaz", Toast.LENGTH_LONG).show()
            } else {
                viewModel.login(mail.toString().trim(), password.toString().trim())
            }
        }
    }

    private fun observerData() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    state.isLoggedIn.let { isLoggedIn ->
                        if (isLoggedIn == true && state.userId != null) {
                            Toast.makeText(requireContext(), "Ba??ar??l??", Toast.LENGTH_SHORT)
                                .show()
                            SharedPref.login(requireActivity(), isLoggedIn, state.userId)
                            actionLoginToHome()
                        }
                    }
                    state.isLoading.let {
                        if (it == true) {
                            binding.progressBarLogin.visibility = View.VISIBLE
                        } else {
                            binding.progressBarLogin.visibility = View.GONE
                        }
                    }
                    state.error.let { error ->
                        if (error != null) {
                            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    private fun actionLoginToHome() {
        val action = LoginFragmentDirections.actionLoginToHome()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}