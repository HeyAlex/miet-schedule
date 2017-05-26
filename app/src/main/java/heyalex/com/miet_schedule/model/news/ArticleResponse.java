package heyalex.com.miet_schedule.model.news;

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


    /**
     * Class that contains all articles
     * Part of {@link ArticleResponse}
     */
    @Root(name = "channel", strict = false)
    private static class Channel {

        @ElementList(inline = true, name = "item")
        private List<Article> articles;
    }


    public List<Article> getAllArticles() {
        return channel.articles;
    }
}