package com.yzpo.crawler.baidutieba.titles;

import java.util.Objects;

public class BaiduTieBaTitle {
    private String title;
    private String url;
    private String author;
    private int comments_num;
    private String last_visit_date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getComments_num() {
        return comments_num;
    }

    public void setComments_num(int comments_num) {
        this.comments_num = comments_num;
    }

    public String getLast_visit_date() {
        return last_visit_date;
    }

    public void setLast_visit_date(String last_visit_date) {
        this.last_visit_date = last_visit_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaiduTieBaTitle that = (BaiduTieBaTitle) o;
        return getComments_num() == that.getComments_num() &&
                Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getUrl(), that.getUrl()) &&
                Objects.equals(getAuthor(), that.getAuthor()) &&
                Objects.equals(getLast_visit_date(), that.getLast_visit_date());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getUrl(), getAuthor(), getComments_num(), getLast_visit_date());
    }

    @Override
    public String toString() {
        return "BaiduTieBaTitle{" +
                "titles='" + title + '\'' +
                ", url='" + url + '\'' +
                ", author='" + author + '\'' +
                ", comments_num=" + comments_num +
                ", last_visit_date='" + last_visit_date + '\'' +
                '}';
    }
}
