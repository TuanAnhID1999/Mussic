package com.phongbm.music.model;

public class Song {
    private String uri;
    private String title;
    private String author;
    private long duration;

    public Song() {
    }

    public Song(String uri, String title, String author, long duration) {
        this.uri = uri;
        this.title = title;
        this.author = author;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Song{" +
                "uri='" + uri + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", duration=" + duration +
                '}';
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

}