package com.hey.mietunoff.mietunofficial.model.news;

import org.simpleframework.xml.Attribute;

/**
 * Contains a url of news picture
 * Part of {@link Article}
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
