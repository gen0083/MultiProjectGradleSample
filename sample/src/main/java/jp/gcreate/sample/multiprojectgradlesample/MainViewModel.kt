package jp.gcreate.sample.multiprojectgradlesample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor():  ViewModel() {
    private val _state = MutableStateFlow(1)
    val state = _state.asStateFlow()

    fun increment() {
        viewModelScope.launch { _state.update { it + 1 } }
    }
}