package com.alex.miet.miet_api.data.news

import org.simpleframework.xml.Attribute

/**
 * Contains a url of news picture
 * Part of [Article]
 */
class Enclosure {
    @Attribute(name = "url")
    var url: String? = null

    @Attribute(name = "length")
    private val length: Long = 0

    @Attribute(name = "type")
    private val type: String? = null
}