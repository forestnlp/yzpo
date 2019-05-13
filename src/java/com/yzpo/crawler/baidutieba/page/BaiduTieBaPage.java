package com.yzpo.crawler.baidutieba.page;

import java.util.Objects;

public class BaiduTieBaPage {

    private String author;
    private String publisher_time;
    private String publisher_src;
    private String content;
    private String floor;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher_time() {
        return publisher_time;
    }

    public void setPublisher_time(String publisher_time) {
        this.publisher_time = publisher_time;
    }

    public String getPublisher_src() {
        return publisher_src;
    }

    public void setPublisher_src(String publisher_src) {
        this.publisher_src = publisher_src;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaiduTieBaPage that = (BaiduTieBaPage) o;
        return Objects.equals(getAuthor(), that.getAuthor()) &&
                Objects.equals(getPublisher_time(), that.getPublisher_time()) &&
                Objects.equals(getPublisher_src(), that.getPublisher_src()) &&
                Objects.equals(getContent(), that.getContent()) &&
                Objects.equals(getFloor(), that.getFloor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthor(), getPublisher_time(), getPublisher_src(), getContent(), getFloor());
    }

    @Override
    public String toString() {
        return "BaiduTieBaPage{" +
                "author='" + author + '\'' +
                ", publisher_time='" + publisher_time + '\'' +
                ", publisher_src='" + publisher_src + '\'' +
                ", content='" + content + '\'' +
                ", floor='" + floor + '\'' +
                '}';
    }
}
