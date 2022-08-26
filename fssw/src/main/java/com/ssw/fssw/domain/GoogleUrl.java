package com.ssw.fssw.domain;

public class GoogleUrl {
    private String url, title, content;
    public GoogleUrl(String url, String title, String content) {
        this.url = url;
        this.title = title;
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
