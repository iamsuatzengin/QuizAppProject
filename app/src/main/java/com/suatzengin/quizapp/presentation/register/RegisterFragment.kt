package com.suatzengin.quizapp.presentation.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.suatzengin.quizapp.R
import com.suatzengin.quizapp.databinding.FragmentRegisterBinding
import com.suatzengin.quizapp.domain.model.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        register()
        observeData()
    }

    private fun register(){
        val name = binding.inputLayoutName.editText?.text
        val surname = binding.inputLayoutSurname.editText?.text
        val mail = binding.inputLayoutMailRegister.editText?.text
        val password = binding.inputLayoutPasswordRegister.editText?.text

        binding.btnSignUpRegister.setOnClickListener {
            if (name != null && surname != null && mail != null && password != null) {
                if (name.isEmpty() || surname.isEmpty() || mail.isEmpty() || password.isEmpty()) {
                    Toast.makeText(requireContext(), "Boş olamaz", Toast.LENGTH_LONG).show()
                } else {
                    val user = User(
                        userId = 0,
                        name = name.toString().trim(), surname = surname.toString().trim(),
                        mail = mail.toString().trim(), password = password.toString().trim()
                    )
                    viewModel.register(user)
                }
            }
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    state.isRegister.let { isRegister ->
                        if (isRegister == true) {
                            Toast.makeText(requireContext(), "Kayıt başarılı!", Toast.LENGTH_SHORT)
                                .show()
                            actionRegisterToLogin()
                        }
                    }
                    state.error.let { error ->
                        if (error != null) {
                            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
                        }
                    }
                    state.isLoading.let { isLoading ->
                        if (isLoading == true) {
                            binding.progressBar.visibility = View.VISIBLE
                        } else {
                            binding.progressBar.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun actionRegisterToLogin() {
        val action = RegisterFragmentDirections.actionRegisterToLogin()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}