package com.hitvaani

import com.devuss.common.Loading
import com.devuss.common.State
import com.devuss.common.Success
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.hitvaani.BookInfo

fun main() {
    VaaniPathViewModel()
}

class VaaniPathViewModel {

    val scope = CoroutineScope(Dispatchers.Unconfined)

    val books: MutableStateFlow<State<List<BookInfo>>> = MutableStateFlow(Loading(emptyList()))

    init {
        fetchBooks()
    }

    private fun fetchBooks() {
        scope.launch {
            books.onEach {
                window.alert("Loaded!! $it")
            }
        }
        scope.launch {
            books.emit(Success(listOf(BookInfo("1", "Title", "Subtitle", emptyList()))))
        }
    }

}