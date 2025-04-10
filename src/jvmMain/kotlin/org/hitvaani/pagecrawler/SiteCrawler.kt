package org.hitvaani.pagecrawler

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.compression.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import org.hitvaani.kb.KnowledgeBase
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.util.*

interface IPageContentEvent

fun IPageContentEvent.getMessage(): String {
    return when (this) {
        is FetchError -> msg
        is PageContent -> "Page: $url, Words: ${words.size}, Links: ${pageLinks.size}, Scripts: ${scripts.size}, Meta: ${metaDescription.size}"
        else -> "Unknown"
    }
}

data class FetchError(val msg: String = "Unable to Fetch page") : IPageContentEvent

data class PageContent(
    var url: String,
    var title: String,
    var lang: String = "en",
    var rawHtml: String = "",
    var metaKeywords: Set<String?> = emptySet(),
    var metaDescription: Set<String?> = emptySet(),
    var words: Set<String> = mutableSetOf(),
    var pageLinks: Set<String> = emptySet(),
    var scripts: List<ScriptInfo> = emptyList(),
) : IPageContentEvent

data class ScriptInfo(
    var src: String? = null, var content: String? = null, var type: String? = null
)

private val WhiteSpaceRegex = Regex("\\s+")
private val WordsRegex = Regex("\\w+")
private val EnSentensesRegex = Regex("[.!?]")
private val HiSentensesRegex = Regex("[।!॥]")

fun HttpRequestBuilder.addChromeHeaders() {
    headers {
        append(
            "User-Agent",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36"
        )
        append(
            "Accept",
            "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7"
        )
        append("Accept-Language", "en-US,en-IN;q=0.9,en;q=0.8,hi;q=0.7")
        append("Accept-Encoding", "gzip, deflate")
        append("Connection", "keep-alive")
        append("Upgrade-Insecure-Requests", "1")
        append("DNT", "1")
        append("Sec-Fetch-Site", "none")
        append("Sec-Fetch-Mode", "navigate")
        append("Sec-Fetch-User", "?1")
        append("Sec-Fetch-Dest", "document")
        append("sec-ch-ua", "\"Chromium\";v=\"134\", \"Not:A-Brand\";v=\"24\", \"Google Chrome\";v=\"134\"")
        append("sec-ch-ua-mobile", "?0")
        append("sec-ch-ua-platform", "macOS")
        append("Cache-Control", "max-age=0")
    }
}


object SiteCrawler {

    var isRunning = false
    private val scope = CoroutineScope(Dispatchers.IO)
    val client = HttpClient(CIO) {
        Charsets {
            // Allow using `UTF_8`.
            register(Charsets.UTF_8)
            // Allow using `ISO_8859_1` with quality 0.1.
            register(Charsets.ISO_8859_1, quality = 0.1f)
        }
        install(ContentEncoding) {
            deflate(1.0F)
            gzip(0.9F)
        }
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }

        engine {
            requestTimeout = 1000
        }

    }

    private val queue = ArrayDeque<String>()
    val pageContent = MutableSharedFlow<IPageContentEvent?>()

    suspend fun submitUrl(url: String) {
        KnowledgeBase.getPersistedContent(url)?.let {
            pageContent.emit(crawl(url, it.rawHtml))
            return
        }
        queue.add(url)
        if (isRunning.not()) {
            isRunning = true
            scope.launch {
                start()
            }
        }
    }

    private suspend fun start() {
        println("Start crawler")
        while (true) {
            if (!isRunning) {
                println("Crawling stopped")
                break
            }
            if (queue.isEmpty()) {
                println("Queue is empty")
                delay(1000)
                continue
            }
            val url = queue.poll()
            try {
                println("Crawling: $url")
                val response = client.get(url) {
                    addChromeHeaders()
                }
                val html = response.bodyAsText()
                println("Received Response: $url")
                pageContent.emit(crawl(url, html))
            } catch (ex: Exception) {
                println("Error crawling: $url")
                pageContent.emit(FetchError("Error fetching page: $url, ${ex.message}"))
            }
        }
    }

    private fun crawl(url: String, html: String): PageContent {
        println("Crawling page: $url")
        val document = Jsoup.parse(html, url)
        return PageContent(url, document.title()).apply {
            pageLinks = extractLinks(document)
            words = document.body().text().split(WhiteSpaceRegex).toSet()
            rawHtml = html
            metaDescription = extractMetaPage(document)
            metaKeywords = extractMetaPage(document, true)
            scripts = extractScriptsFromPage(document)
            lang = extractLang(document)
            KnowledgeBase.persist(url, this)
        }

    }

    private fun extractLang(document: Document): String {
        return document.root().attr("lang").ifBlank { "en" }
    }

    private fun extractScriptsFromPage(document: Document): List<ScriptInfo> {
        return document.select("script").map {
            ScriptInfo(it.attr("src"), it.html(), it.attr("type"))
        }
    }

    private fun extractMetaPage(doc: Document, keyword: Boolean = false): Set<String?> {
        val meta = mutableSetOf<String?>()
        val name = if (keyword) "keywords" else "description"
        doc.select("meta").forEach {
            if (it.attr("name").equals(name, true)) {
                val desc = it.attr("content")
                if (desc.isNotBlank()) {
                    return desc.split(WhiteSpaceRegex).toSet()
                }
            }
        }
        return meta
    }

    private fun extractLinks(doc: Document): MutableSet<String> {
        val links = mutableSetOf<String>()
        doc.select("a").forEach {
            val link = it.attr("href")
            if (link.startsWith("http")) {
                links.add(link)
            } else {
                links.add(link)
            }
        }
        return links
    }

}