package heyalex.com.miet_schedule.model.news;

import org.simpleframework.xml.Attribute;

/**
 * Created by alexf on 29.10.2016.
 */

public class Enclosure {

    @Attribute(name = "url")
    private String url;

    @Attribute(name = "length")
    private long length;

    @Attribute(name = "type")
    private String type;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
