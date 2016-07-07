package com.theironyard.controllers;

import com.theironyard.entities.Fav;
import com.theironyard.entities.TomALike;
import com.theironyard.entities.User;
import com.theironyard.services.FavRepository;
import com.theironyard.services.TomALikeRepository;
import com.theironyard.services.UserRepository;
import com.theironyard.utils.PasswordStorage;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.sql.SQLException;

/**
 * Created by zach on 7/7/16.
 */
@RestController
public class MagnumApiController {
    public static String PHOTOS_DIR = "photos";

    @Autowired
    TomALikeRepository toms;

    @Autowired
    UserRepository users;

    @Autowired
    FavRepository favs;

    @PostConstruct
    public void init() throws SQLException {
        Server.createWebServer().start();
    }

    @RequestMapping(path = "/tomalikes", method = RequestMethod.GET)
    public Iterable<TomALike> getToms() {
        return toms.findAll();
    }

    @RequestMapping(path = "/tomalikes", method = RequestMethod.POST)
    public void addTom(HttpSession session, MultipartFile photo, String comment, HttpServletResponse response) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("Not logged in!");
        }

        User user = users.findByName(username);
        if (user == null) {
            throw new Exception("User not in database!");
        }

        File dir = new File("public/" + PHOTOS_DIR);
        dir.mkdirs();

        File photoFile = File.createTempFile("photo", photo.getOriginalFilename(), dir);
        FileOutputStream fos = new FileOutputStream(photoFile);
        fos.write(photo.getBytes());

        TomALike tom = new TomALike(photoFile.getName(), comment, user);
        toms.save(tom);

        response.sendRedirect("/#/tomalikes");
    }

    @RequestMapping(path = "/favs", method = RequestMethod.POST)
    public void addFav(HttpSession session, @RequestBody Fav fav) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("Not logged in!");
        }

        User user = users.findByName(username);
        if (user == null) {
            throw new Exception("User not in database!");
        }

        TomALike tom = toms.findOne(fav.getTomId());
        if (tom == null) {
            throw new Exception("Can't find that tom!");
        }

        tom.setVotes(tom.getVotes() + (fav.isLooksLikeTom() ? 1 : -1));
        toms.save(tom);

        fav.setTomALike(tom);
        fav.setUser(user);
        favs.save(fav);
    }

    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public void addUser(HttpSession session, @RequestBody User user) throws Exception {
        User userFromDb = users.findByName(user.getName());
        if (userFromDb == null) {
            user.setPassword(PasswordStorage.createHash(user.getPassword()));
            users.save(user);
        }
        else if (!PasswordStorage.verifyPassword(user.getPassword(), userFromDb.getPassword())) {
            throw new Exception("Incorrect password");
        }
        session.setAttribute("username", user.getName());
    }
}
