package com.geeks.mvp.presentation.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.geeks.mvp.presentation.state.UiState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseFragment<T> : Fragment() {
    abstract fun getBinding(inflater: LayoutInflater, container: ViewGroup?): View
    protected fun collectUiState(
        state: StateFlow<UiState<T>>,
        onSuccess: (T) -> Unit,
        onError: (String) -> Unit = { showToast(it) },
        onLoading: (() -> Unit)? = null,
        onEmpty:() -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            state.collect { uiState ->
                when (uiState) {
                    is UiState.Loading -> onLoading?.let { it() }
                    is UiState.Success -> onSuccess(uiState.data)
                    is UiState.Error -> onError(uiState.message)
                    is UiState.Empty -> onEmpty()
                    else -> Unit
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}