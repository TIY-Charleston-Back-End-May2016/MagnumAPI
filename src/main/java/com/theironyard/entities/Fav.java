package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by zach on 7/7/16.
 */
@Entity
@Table(name = "favs")
public class Fav {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    boolean looksLikeTom;

    @ManyToOne
    User user;

    @ManyToOne
    TomALike tomALike;

    @Transient
    Integer tomId;

    public Fav() {
    }

    public Fav(boolean looksLikeTom, User user, TomALike tomALike) {
        this.looksLikeTom = looksLikeTom;
        this.user = user;
        this.tomALike = tomALike;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLooksLikeTom() {
        return looksLikeTom;
    }

    public void setLooksLikeTom(boolean looksLikeTom) {
        this.looksLikeTom = looksLikeTom;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TomALike getTomALike() {
        return tomALike;
    }

    public void setTomALike(TomALike tomALike) {
        this.tomALike = tomALike;
    }

    public Integer getTomId() {
        return tomId;
    }

    public void setTomId(Integer tomId) {
        this.tomId = tomId;
    }
}
