package com.suatzengin.quizapp.presentation.home


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.suatzengin.quizapp.R
import com.suatzengin.quizapp.common.SharedPref
import com.suatzengin.quizapp.databinding.FragmentHomeBinding
import com.suatzengin.quizapp.presentation.home.adapter.HomeRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: HomeRecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = SharedPref.getUserId(requireActivity())

        viewModel.getQuiz(userId)

        observeData()

        binding.quiz.setOnClickListener {
            val action = HomeFragmentDirections.actionToQuizFragment()
            findNavController().navigate(action)
        }
        binding.addQuestion.setOnClickListener {
            val action = HomeFragmentDirections.actionAddQuestion()
            findNavController().navigate(action)
        }

        binding.logout.setOnClickListener {
            logout()
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    state.quizzes.let {
                        adapter.submitList(it)
                    }
                    state.isLoading.let { isLoading ->
                        if (isLoading == true) {
                            binding.pbHome.visibility = View.VISIBLE
                        } else {
                            binding.pbHome.visibility = View.GONE
                        }
                    }
                    if (state.error != null) {
                        Toast.makeText(requireContext(), "${state.error}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private fun logout() {
        SharedPref.logout(requireActivity())
        val action = HomeFragmentDirections.actionLoginFragment()
        findNavController().navigate(action)
    }

    private fun setupRecyclerView() {
        adapter = HomeRecyclerView()
        binding.rvHome.adapter = adapter
        binding.rvHome.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}