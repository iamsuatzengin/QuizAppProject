package com.suatzengin.quizapp.presentation.add_question.add_answer

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.suatzengin.quizapp.databinding.FragmentAddAnswerBinding
import com.suatzengin.quizapp.domain.model.Answer
import com.suatzengin.quizapp.presentation.add_question.AddQuestionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddAnswerFragment : Fragment() {

    private var _binding: FragmentAddAnswerBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddQuestionViewModel by viewModels()
    private val args: AddAnswerFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddAnswerBinding.inflate(layoutInflater, container, false)
        onTextFocusedChangeButtonVisibility()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etAnswerA = binding.etAnswerA
        val etAnswerB = binding.etAnswerB
        val etAnswerC = binding.etAnswerC
        val etAnswerD = binding.etAnswerD

        binding.btnAnswerA.setOnClickListener {
            addAnswer(etAnswerA)
        }
        binding.btnAnswerB.setOnClickListener {
            addAnswer(etAnswerB)
        }
        binding.btnAnswerC.setOnClickListener {
            addAnswer(etAnswerC)
        }
        binding.btnAnswerD.setOnClickListener {
            addAnswer(etAnswerD)
            backToAddQuestion()
        }

        binding.btnBack.setOnClickListener {
            backToAddQuestion()
        }
    }

    private fun backToAddQuestion(){
        val action = AddAnswerFragmentDirections.actionToAddQuestionFragmentBack()
        findNavController().navigate(action)
    }

    private fun addAnswer(answerText: TextView) {

        val builder = AlertDialog.Builder(requireContext())
        builder
            .setTitle("Ekle")
            .setMessage("${answerText.text} cevabını doğru veya yanlış olarak işartle!")
            .setPositiveButton("Doğru") { _, _ ->
                val answer =
                    Answer(
                        answerId = 0,
                        questionId = args.questionId,
                        answerText = answerText.text.toString(),
                        isCorrect = true
                    )
                viewModel.addAnswer(answer)
            }.setNegativeButton("Yanlış") { _, _ ->
                val answer =
                    Answer(
                        answerId = 0,
                        questionId = args.questionId,
                        answerText = answerText.text.toString(),
                        isCorrect = false
                    )
                viewModel.addAnswer(answer)
            }
        builder.create().show()
    }

    private fun onTextFocusedChangeButtonVisibility() {
        binding.apply {
            etAnswerA.setOnFocusChangeListener { _, b ->
                if (b) {
                    btnAnswerA.visibility = View.VISIBLE
                } else {
                    btnAnswerA.visibility = View.GONE
                }
            }
            etAnswerB.setOnFocusChangeListener { _, b ->
                if (b) {
                    btnAnswerB.visibility = View.VISIBLE
                } else {
                    btnAnswerB.visibility = View.GONE
                }
            }
            etAnswerC.setOnFocusChangeListener { _, b ->
                if (b) {
                    btnAnswerC.visibility = View.VISIBLE
                } else {
                    btnAnswerC.visibility = View.GONE
                }
            }
            etAnswerD.setOnFocusChangeListener { _, b ->
                if (b) {
                    btnAnswerD.visibility = View.VISIBLE
                } else {
                    btnAnswerD.visibility = View.GONE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}