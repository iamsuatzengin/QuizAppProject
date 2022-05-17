package com.suatzengin.quizapp.presentation.quiz


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.suatzengin.quizapp.common.SharedPref
import com.suatzengin.quizapp.data.local.relations.QuestionWithAnswers
import com.suatzengin.quizapp.databinding.FragmentQuizBinding
import com.suatzengin.quizapp.domain.model.Quiz
import com.suatzengin.quizapp.presentation.quiz.adapter.QuizRecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizFragment : Fragment() {
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    private val viewModel: QuizViewModel by viewModels()
    private lateinit var adapter: QuizRecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        setupRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.questionWithAnswers.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }

    }

    private fun setupRecyclerView() {
        val layoutManager = object : LinearLayoutManager(
            requireContext(), HORIZONTAL, false
        ) {
            override fun canScrollHorizontally(): Boolean {
                return false
            }
        }
        adapter = QuizRecyclerView({ questionWithAnswer, index ->
            onClickNextButton(layoutManager, questionWithAnswer, index)
        }, { questionWithAnswers, index ->
            onFinishButton(questionWithAnswers, index)
        })
        binding.rvQuiz.adapter = adapter
        binding.rvQuiz.layoutManager = layoutManager
    }

    private fun onClickNextButton(
        layoutManager: LinearLayoutManager,
        questionWithAnswer: QuestionWithAnswers,
        index: Int,
    ) {
        viewModel.verifyAnswer(questionWithAnswer, index)
        if (layoutManager.findLastCompletelyVisibleItemPosition() < (adapter.itemCount - 1)) {
            layoutManager.scrollToPosition(layoutManager.findLastCompletelyVisibleItemPosition() + 1)
        }
    }

    private fun onFinishButton(questionWithAnswers: QuestionWithAnswers, index: Int) {
        viewModel.verifyAnswer(questionWithAnswers, index)
        val totalScore = viewModel.totalScore
        val totalCorrect = viewModel.totalCorrect
        val totalWrong = viewModel.totalWrong

        val currentUserId = SharedPref.getUserId(requireActivity())
        val quiz = Quiz(
            quizId = 0,
            userId = currentUserId,
            totalScore = totalScore,
            totalCorrect = totalCorrect,
            totalWrong = totalWrong,
        )
        viewModel.addQuiz(quiz)


        Toast.makeText(requireContext(), "bitti", Toast.LENGTH_SHORT).show()
        val action = QuizFragmentDirections.actionToResultFragment(quiz)
        findNavController().navigate(action)
    }

}