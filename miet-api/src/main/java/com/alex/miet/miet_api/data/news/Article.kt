package com.alex.miet.miet_api.data.news

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
class Article {
    /**
     * Title of a news
     */
    @Element(name = "title")
    var title: String? = null

    /**
     * Link that provides an actual infromation about news
     */
    @Element(name = "link")
    var link: String? = null

    /**
     * Description of a news
     */
    @Element(name = "description")
    var description: String? = null

    /**
     * Class that contains a url for news picture
     */
    @Element(name = "enclosure")
    var enclosure: Enclosure? = null

    /**
     * String of representation of date of publication
     */
    @Element(name = "pubDate")
    var pubDate: String? = null
}