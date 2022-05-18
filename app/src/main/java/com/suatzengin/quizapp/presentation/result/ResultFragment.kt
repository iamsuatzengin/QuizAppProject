package com.suatzengin.quizapp.presentation.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.suatzengin.quizapp.R
import com.suatzengin.quizapp.databinding.FragmentResultBinding


class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    private val args: ResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.quiz = args.quiz
        val totalCorrect = args.quiz.totalCorrect
        val totalWrong = args.quiz.totalWrong

        totalCorrect?.let {
            if(totalCorrect >= totalWrong!!){
                binding.tvResult.text = getText(R.string.congrats)
                binding.success.visibility = View.VISIBLE
                binding.fail.visibility = View.INVISIBLE
            }else{
                binding.tvResult.text = getText(R.string.nice_try)
                binding.success.visibility = View.INVISIBLE
                binding.fail.visibility = View.VISIBLE
            }
        }

        binding.btnResultToHome.setOnClickListener {
            val action = ResultFragmentDirections.actionResultToHome()
            findNavController().navigate(action)
        }
    }

}