package kidsTeacher.english.db

import data.WordUiData
import org.jetbrains.exposed.sql.transactions.transaction

object WordsRepo {

    fun getAllWords(): List<WordUiData> = transaction {
        WordEntity.all().map { it.toWord() }.toList()
    }

    fun addWord(word: WordUiData) = transaction {
        WordEntity.new {
            en = word.en
            hi = word.hi
//            group = word.group
            pos = word.pos
        }
    }

}
