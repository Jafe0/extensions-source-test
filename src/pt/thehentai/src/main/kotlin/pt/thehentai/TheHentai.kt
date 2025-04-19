package eu.kanade.tachiyomi.extension.pt.thehentai

import eu.kanade.tachiyomi.source.model.SManga
import eu.kanade.tachiyomi.source.model.SChapter
import eu.kanade.tachiyomi.source.model.Page
import eu.kanade.tachiyomi.source.online.ParsedHttpSource
import okhttp3.Request
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class TheHentai : ParsedHttpSource() {

    override val name = "TheHentai"
    override val baseUrl = "https://thehentai.world"
    override val lang = "pt"
    override val supportsLatest = true

    override fun popularMangaRequest(page: Int) = GET("$baseUrl/manga-list/page/$page", headers)

    override fun popularMangaSelector() = "div.bs div.bsx"

    override fun popularMangaFromElement(element: Element) = SManga.create().apply {
        title = element.select("div.tt").text()
        thumbnail_url = element.select("img").attr("abs:src")
        setUrlWithoutDomain(element.select("a").attr("href"))
    }

    override fun popularMangaNextPageSelector() = "a.next"

    override fun latestUpdatesRequest(page: Int) = GET("$baseUrl/latest/page/$page", headers)

    override fun latestUpdatesSelector() = popularMangaSelector()

    override fun latestUpdatesFromElement(element: Element) = popularMangaFromElement(element)

    override fun latestUpdatesNextPageSelector() = popularMangaNextPageSelector()

    override fun searchMangaRequest(page: Int, query: String) = GET("$baseUrl/?s=$query&page=$page", headers)

    override fun searchMangaSelector() = popularMangaSelector()

    override fun searchMangaFromElement(element: Element) = popularMangaFromElement(element)

    override fun searchMangaNextPageSelector() = popularMangaNextPageSelector()

    override fun mangaDetailsParse(document: Document) = SManga.create().apply {
        title = document.selectFirst("h1.entry-title")?.text().orEmpty()
        description = document.selectFirst("div.desc")?.text()
        thumbnail_url = document.selectFirst("div.thumb img")?.attr("abs:src")
    }

    override fun chapterListSelector() = "ul.main li"

    override fun chapterFromElement(element: Element) = SChapter.create().apply {
        name = element.selectFirst("a")?.text().orEmpty()
        setUrlWithoutDomain(element.selectFirst("a")?.attr("href").orEmpty())
    }

    override fun pageListRequest(chapter: SChapter) = GET(baseUrl + chapter.url, headers)

    override fun pageListParse(document: Document): List<Page> {
        return document.select("div.reader-area img").mapIndexed { index, element ->
            Page(index, imageUrl = element.attr("abs:src"))
        }
    }

    override fun imageUrlParse(document: Document) = throw UnsupportedOperationException("Not used")
}
