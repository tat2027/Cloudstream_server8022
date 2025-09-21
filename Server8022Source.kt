package com.cloudstream.server8022

import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

/**
 * Simple example Cloudstream-style source.
 * Package: com.cloudstream.server8022
 *
 * NOTE: Adapt selectors to the actual HTML structure of http://84.54.185.70:8022/
 */
class Server8022Source {

    private val baseUrl = "http://84.54.185.70:8022"
    private val client = OkHttpClient()

    // Fetches the HTML of a page
    @Throws(IOException::class)
    fun fetchPage(path: String = "/"): Document {
        val url = if (path.startsWith("http")) path else (baseUrl + path)
        val request = Request.Builder()
            .url(url)
            .header("User-Agent", "Mozilla/5.0 (compatible)")
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code \$response")
            val body = response.body?.string() ?: ""
            return Jsoup.parse(body, baseUrl)
        }
    }

    // Example: list items from home page
    fun listHomeItems(): List<HomeItem> {
        val doc = fetchPage("/")
        val items = mutableListOf<HomeItem>()
        // TRY generic selectors; adjust as needed
        val elements = doc.select("div.item, .card, article, .movie, .series")
        for (el in elements) {
            val title = el.selectFirst("h3, h2, .title")?.text() ?: el.selectFirst("a")?.text() ?: "Untitled"
            val link = el.selectFirst("a")?.attr("href") ?: ""
            val thumb = el.selectFirst("img")?.attr("src") ?: ""
            items.add(HomeItem(title.trim(), link, thumb))
        }
        return items
    }

    data class HomeItem(val title: String, val url: String, val thumbnail: String)
}
