package heyalex.com.miet_schedule.model.news;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "rss", strict = false)
public class ArticleResponse {

    @Element(name = "channel")
    private Channel channel;


    @Root(name = "channel", strict = false)
    static class Channel {

        @ElementList(inline = true, name = "item")
        private List<Article> articles;
    }


    public List<Article> getAllArticles() {
        return channel.articles;
    }
}