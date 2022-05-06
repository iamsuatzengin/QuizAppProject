package com.suatzengin.quizapp.presentation.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suatzengin.quizapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCikis.setOnClickListener {
            logout()
        }
    }

    private fun logout(){
        val sharedPref = activity?.getSharedPreferences("logged_in", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()){
            putBoolean("isLoggedIn", false)
            apply()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}