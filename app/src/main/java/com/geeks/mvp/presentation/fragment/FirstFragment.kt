package com.geeks.mvp.presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geeks.mvp.databinding.FragmentFirstBinding
import com.geeks.mvp.presentation.viewmodel.FirstViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class FirstFragment : Fragment() {

    private val viewModel: FirstViewModel by viewModel()

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.counter.observe(viewLifecycleOwner) { counter ->
            binding.tvCounter.text = counter.count.toString()
        }

        binding.decrementBtn.setOnClickListener {
            viewModel.decrement()
        }

        binding.incrementBtn.setOnClickListener {
            viewModel.increment()
        }

    }


}