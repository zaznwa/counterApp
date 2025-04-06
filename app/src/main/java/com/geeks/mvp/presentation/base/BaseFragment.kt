package com.geeks.mvp.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.geeks.mvp.R
import com.geeks.mvp.presentation.utils.UiState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

typealias Inflater<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel>(
    private val inflate: Inflater<VB>,
) : Fragment() {


    private var _binding: VB? = null
    protected val binding get() = _binding!!
    protected open val viewModel: BaseViewModel by viewModel()
    protected open val progressBar: View get() = binding.root.findViewById(R.id.progressBar)
    protected inline fun <reified T : VM> vm(): Lazy<T> {
        return viewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupListeners()
        setupCollectors()
        setupRequests()
    }

    protected open fun setupUI() {}

    protected open fun setupListeners() {}

    protected open fun setupCollectors() {}

    protected open fun setupRequests() {}


    protected fun <T> StateFlow<UiState<T>>.collectState(
        state: (UiState<T>) -> Unit,
        showSuccess: (data: T) -> Unit,
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                this@collectState.collect { uiState ->
                    state(uiState)
                    when (uiState) {
                        is UiState.Success -> showSuccess(uiState.data)
                        is UiState.Error -> showError(uiState.message)
                        else -> {}
                    }
                }
            }
        }
    }

    protected fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }
    protected fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
