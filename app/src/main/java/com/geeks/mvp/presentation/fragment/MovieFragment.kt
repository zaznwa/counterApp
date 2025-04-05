package com.geeks.mvp.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.geeks.mvp.databinding.FragmentMovieBinding
import com.geeks.mvp.domain.model.MovieEntity
import com.geeks.mvp.presentation.base.BaseFragment
import com.geeks.mvp.presentation.state.UiState
import com.geeks.mvp.presentation.viewmodel.MovieViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieFragment : BaseFragment<List<MovieEntity>>() {

    private val movieViewModel: MovieViewModel by viewModel()
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.visibility = View.GONE
        binding.btnSearch.setOnClickListener {
            val query = binding.etSearch.text.toString()
            if (query.isNotEmpty()) {
                Log.d("ololo", "Отправляю запрос: query=$query, page=1, limit=10")
                movieViewModel.searchMovies(query)
            } else {
                Toast.makeText(requireContext(), "Введите запрос", Toast.LENGTH_SHORT).show()
            }
        }

        collectUiState(
            state = movieViewModel.movieState,
            onSuccess = { movies ->
                Log.d("ololo", "Полученные фильмы: $movies")
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
            },
            onLoading = {
                binding.progressBar.visibility = View.VISIBLE
            },
            onError = {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            },
            onEmpty = {
                binding.progressBar.visibility = View.GONE
                binding.tvMovieTitle.text = "Фильмы не найдены"
                binding.tvMovieDescription.text = ""
                binding.imgMoviePoster.setImageDrawable(null)
            }
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}