package com.theironyard.controllers;

import com.theironyard.entities.TomALike;
import com.theironyard.services.TomALikeRepository;
import com.theironyard.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by zach on 7/7/16.
 */
@RestController
public class MagnumApiController {
    @Autowired
    TomALikeRepository tomALikes;

    @Autowired
    UserRepository users;

    // GET /tomalikes
    // POST /tomalikes
    // POST /favs
    // POST /users

    @RequestMapping(path = "/tomalikes", method = RequestMethod.GET)
    public Iterable<TomALike> getToms() {
        return tomALikes.findAll();
    }

    @RequestMapping(path = "/tomalikes", method = RequestMethod.POST)
    public void addTom(HttpSession session) {
        String username = (String) session.getAttribute("username");
        // grab user from db
        // save to db
    }
}
