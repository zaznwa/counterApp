package com.geeks.mvp.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.geeks.mvp.databinding.FragmentMovieBinding
import com.geeks.mvp.presentation.base.BaseFragment
import com.geeks.mvp.presentation.base.BaseViewModel
import com.geeks.mvp.presentation.utils.UiState
import com.geeks.mvp.presentation.viewmodel.MovieViewModel


class MovieFragment : BaseFragment<FragmentMovieBinding, MovieViewModel>(
    FragmentMovieBinding::inflate
) {

    override val viewModel: MovieViewModel by vm()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCollectors()
    }

    override fun setupListeners() {
        binding.btnSearch.setOnClickListener {
            val query = binding.etSearch.text.toString()
            if (query.isNotEmpty()) {
                viewModel.searchMovies(query)
            } else {
                Toast.makeText(requireContext(), "Введите запрос", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun setupCollectors() {
        viewModel.movieState.collectState(
            state = { state ->
                if (state is UiState.Loading) {
                    showProgressBar()
                } else {
                    hideProgressBar()
                }
            },
            showSuccess = { movies ->
                val movie = movies.firstOrNull()
                if (movie != null) {
                    binding.tvMovieTitle.text = movie.name
                    binding.tvMovieDescription.text = movie.description
                    Glide.with(this@MovieFragment)
                        .load(movie.posterUrl)
                        .into(binding.imgMoviePoster)
                } else {
                    binding.tvMovieTitle.text = "Фильмы не найдены"
                    binding.tvMovieDescription.text = ""
                    binding.imgMoviePoster.setImageDrawable(null)
                }
            }
        )
    }
}
