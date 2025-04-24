package kidsTeacher.english

import data.GroupUiData
import data.WordUiData
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import logger.jsLog
import network.ResponseWrapper
import network.apiClient

class DictionaryViewModel {

    val scope = CoroutineScope(Dispatchers.Default)
    val words = MutableStateFlow<List<WordUiData>>(emptyList())
    val groups = MutableStateFlow<List<GroupUiData>>(emptyList())

    fun fetchGroups() {
        scope.launch {
            apiClient.get("/en/api/groups").body<ResponseWrapper<List<GroupUiData>>>().payload?.let {
                groups.emit(it)
            }
        }
    }

    init {
        jsLog("WordMeaningViewModel initialized")
        fetchGroups()
    }

    fun loadWords(group: GroupUiData) {
        scope.launch {
            apiClient.get("/en/api/words/${group._id}").body<ResponseWrapper<List<WordUiData>>>().payload?.let {
                words.emit(it)
            }
        }
    }

}