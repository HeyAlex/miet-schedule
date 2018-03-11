package com.alex.miet.mobile.model.news;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * POJO that contains all information about news
 */
@Root(name = "rss", strict = false)
public class ArticleResponse {

    /**
     * Channel contains list of news
     */
    @Element(name = "channel")
    private Channel channel;

    public List<Article> getAllArticles() {
        return channel.articles;
    }

    /**
     * Class that contains all articles
     * Part of {@link ArticleResponse}
     */
    @Root(name = "channel", strict = false)
    private static class Channel {

        @ElementList(inline = true, name = "item")
        private List<Article> articles;
    }
}