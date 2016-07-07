package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by zach on 7/7/16.
 */
@Entity
@Table(name = "tom_a_likes")
public class TomALike {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String filename;

    @Column(nullable = false)
    String comment;

    @ManyToOne
    User user;

    public TomALike() {
    }

    public TomALike(String filename, String comment, User user) {
        this.filename = filename;
        this.comment = comment;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
