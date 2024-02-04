package jp.gcreate.sample.multiprojectgradlesample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gcreate.sample.multiprojectgradlesample.db.BookDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val bookDao: BookDao
):  ViewModel() {
    private val _state = MutableStateFlow(1)
    val state = _state.asStateFlow()
    private val _book = MutableStateFlow("")
    val book = _book.asStateFlow()

    fun increment() {
        viewModelScope.launch { _state.update { it + 1 } }
    }

    fun getBook() {
        val result = bookDao.get("test")
        result?.let { _book.value = it.title }
    }
}