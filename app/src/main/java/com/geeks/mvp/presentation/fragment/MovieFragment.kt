package com.geeks.mvp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.geeks.mvp.databinding.FragmentMovieBinding
import com.geeks.mvp.presentation.viewmodel.MovieViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieFragment : Fragment() {

    private val movieViewModel: MovieViewModel by viewModel()
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnSearch.setOnClickListener {
            val query = binding.etSearch.text.toString()
            if (query.isNotEmpty()) {
                movieViewModel.searchMovies(query)
            } else {
                Toast.makeText(requireContext(), "Введите запрос", Toast.LENGTH_SHORT).show()
            }
        }

        lifecycleScope.launch {
            movieViewModel.movies.collect { movies ->
                if (movies.isNotEmpty()) {
                    binding.tvMovieTitle.text = movies.first().name
                    binding.tvMovieDescription.text = movies.first().description
                    Glide.with(this@MovieFragment)
                        .load(movies.first().posterUrl)
                        .into(binding.imgMoviePoster)
                } else {

                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}