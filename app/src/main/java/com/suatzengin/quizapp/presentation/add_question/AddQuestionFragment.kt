package com.suatzengin.quizapp.presentation.add_question

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.suatzengin.quizapp.R
import com.suatzengin.quizapp.databinding.FragmentAddQuestionBinding
import com.suatzengin.quizapp.domain.model.Answer
import com.suatzengin.quizapp.domain.model.Question
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddQuestionFragment : Fragment() {
    private var _binding: FragmentAddQuestionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddQuestionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddQuestionBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnActionToHome.setOnClickListener {
            val action = AddQuestionFragmentDirections.actionToHomeFragment()
            findNavController().navigate(action)
        }
        binding.btnAddQuestion.setOnClickListener {
            addQuestion()
        }
        observeData()
    }

    private fun addQuestion() {
        val questionText = binding.editTextQuestion.text.toString()
        val question = Question(questionId = 0, text = questionText, nextDate = null, quizId = null)
        viewModel.addQuestion(question)
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    state.questionId?.let {
                        actionToAddAnswer(it.toInt())
                    }
                    state.isAdded.let { isAdded ->
                        if (isAdded == true) {
                            Toast.makeText(requireContext(), "Eklendi!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    state.isLoading.let { isLoading ->
                        if (isLoading == true) {
                            binding.pbAddQuestion.visibility = View.VISIBLE
                        } else {
                            binding.pbAddQuestion.visibility = View.GONE
                        }
                    }
                    if (state.error != null) {
                        Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun actionToAddAnswer(questionId: Int){
        val action = AddQuestionFragmentDirections.actionToAddAnswerFragment(questionId)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}