package com.alex.miet.miet_api.data.news

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

/**
 * POJO that contains all information about news
 */
@Root(name = "rss", strict = false)
class ArticleResponse {
    /**
     * Channel contains list of news
     */
    @Element(name = "channel")
    private val channel: Channel? = null

    fun getAllArticles(): List<Article>? {
        return channel?.allArticles
    }
}

/**
 * Class that contains all articles
 * Part of [ArticleResponse]
 */
@Root(name = "channel", strict = false)
private class Channel {
    @ElementList(inline = true, name = "item")
    val allArticles: List<Article>? = null
}