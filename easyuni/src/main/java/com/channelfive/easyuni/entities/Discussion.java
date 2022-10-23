package com.channelfive.easyuni.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "discussions")
public class Discussion {
    
    @Id
    private String id;

    private String title;

    @DocumentReference
    private Account author;

    private String message;

    @DocumentReference
    private List<DiscussionReply> replies;

    private LocalDateTime date;

    private Integer viewCount;

    public Discussion() {
        date = LocalDateTime.now();
        viewCount = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Account getAuthor() {
        return author;
    }

    public void setAuthor(Account author) {
        this.author = author;
    }

    public List<DiscussionReply> getReplies() {
        return replies;
    }

    public void setReplies(List<DiscussionReply> replies) {
        this.replies = replies;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
