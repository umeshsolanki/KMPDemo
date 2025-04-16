package org.hitvaani.kb

import org.hitvaani.pagecrawler.PageContent
import org.hitvaani.utils.GsonUtils
import java.io.File
import java.io.FileOutputStream
import kotlin.collections.set

object KnowledgeBase {
    private val words = mutableSetOf<String>()
    private val sites = mutableMapOf<String, PageContent>()
    private val links = mutableMapOf<String, Set<String>>()

    fun addWord(word: String) {
        words.add(word)
    }

    fun addSite(url: String, pageContent: PageContent) {
        sites[url] = pageContent
    }

    fun persist(url: String, pageContent: PageContent) {
        if (File("data").exists().not()) {
            File("data").mkdirs()
        }
        FileOutputStream(getFileName(url)).use {
            println("Persisting ${it.fd}")
            it.write(GsonUtils.toJsonString(pageContent).toByteArray())
        }
    }

    private fun getFileName(url: String): String {
        return "data/${url.replace("/", "#")}.json"
    }

    fun getPersistedContent(url: String): PageContent? {
        File(getFileName(url)).let {
            if (it.exists()) {
                return GsonUtils.fromJsonString(it.readText())
            }
        }
        return null
    }

    fun addLink(from: String, to: String) {
        links[from] = links[from]?.plus(to) ?: setOf(to)
    }

    fun persist() {
        println("Persisting KB")
        GsonUtils.toJsonString(KnowledgeBaseMeta(words.size.toLong(), sites.size.toLong(), links.size.toLong()))
    }

    fun getMeta(): KnowledgeBaseMeta {
        return KnowledgeBaseMeta(words.size.toLong(), sites.size.toLong(), links.size.toLong())
    }

    fun getWords(): Set<String> {
        return words
    }

    fun getSites(): Map<String, PageContent> {
        return sites
    }

    fun getLinks(): Map<String, Set<String>> {
        return links
    }
}


data class KnowledgeBaseMeta(
    var words: Long, var sites: Long, var links: Long
)