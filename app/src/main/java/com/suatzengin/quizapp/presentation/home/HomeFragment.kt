package com.suatzengin.quizapp.presentation.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.suatzengin.quizapp.common.SharedPref
import com.suatzengin.quizapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

        val userId = SharedPref.getUserId(requireActivity())
        binding.textView.text = userId.toString()

        binding.btnCikis.setOnClickListener {
            SharedPref.logout(requireActivity())
        }
        binding.btnAdd.setOnClickListener {
            val action = HomeFragmentDirections.actionAddQuestion()
            findNavController().navigate(action)
        }
        binding.btnQuiz.setOnClickListener {
            val action = HomeFragmentDirections.actionToQuizFragment()
            findNavController().navigate(action)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}