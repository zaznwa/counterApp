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
import com.geeks.mvp.domain.model.MovieEntity
import com.geeks.mvp.presentation.ui.UiState
import com.geeks.mvp.presentation.viewmodel.MovieViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieFragment : Fragment() {

    private val movieViewModel: MovieViewModel by viewModel()
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
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
            movieViewModel.movieState.collect { state ->
                when (state) {
                    is UiState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is UiState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        showMovies(state.data)
                    }
                    is UiState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                    }
                    is UiState.Empty -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), "Фильмы не найдены", Toast.LENGTH_SHORT).show()
                    }
                    UiState.Initial -> {

                    }
                }
            }
        }
    }

    private fun showMovies(movies: List<MovieEntity>) {
        if (movies.isNotEmpty()) {
            val firstMovie = movies.first()
            binding.tvMovieTitle.text = firstMovie.name
            binding.tvMovieDescription.text = firstMovie.description

            if (firstMovie.posterUrl.isNotEmpty()) {
                Glide.with(this)
                    .load(firstMovie.posterUrl)
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .into(binding.imgMoviePoster)
            } else {
                binding.imgMoviePoster.setImageResource(android.R.drawable.ic_menu_gallery)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}