package com.alex.miet.mobile.model.news;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public class Article {

    /**
     * Title of a news
     */
    @Element(name = "title")
    private String title;

    /**
     * Link that provides an actual infromation about news
     */
    @Element(name = "link")
    private String link;

    /**
     * Description of a news
     */
    @Element(name = "description")
    private String description;

    /**
     * Class that contains a url for news picture
     */
    @Element(name = "enclosure")
    private Enclosure enclosure;

    /**
     * String of representation of date of publication
     */
    @Element(name = "pubDate")
    private String pubDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Enclosure getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

}