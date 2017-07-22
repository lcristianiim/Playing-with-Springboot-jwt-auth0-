package com.associations.controllers;

import com.google.gson.Gson;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 16.07.2017.
 */
@RestController
@Component
@RequestMapping(value = "/api")
public class Photos {

    @RequestMapping(value = "/login")
    @ResponseBody
    public String login() {
        return "All good. You DO NOT need to be authenticated to call /login";
    }

    @CrossOrigin
    @RequestMapping(value = "/photos", method = RequestMethod.GET)
    @ResponseBody
    public void getPhotos(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Object jwtClaims = req.getAttribute("jwt");
        String json = new Gson().toJson(jwtClaims);
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(json);
    }

    @RequestMapping(value = "/photos", method = RequestMethod.POST)
    @ResponseBody
    public String createPhotos() {
        return "All good. You can see this because you are Authenticated with a Token granted the 'create:photos' scope";
    }

    @RequestMapping(value = "/photos", method = RequestMethod.PUT)
    @ResponseBody
    public String updatePhotos() {
        return "All good. You can see this because you are Authenticated with a Token granted the 'update:photos' scope";
    }

    @RequestMapping(value = "/photos", method = RequestMethod.DELETE)
    @ResponseBody
    public String deletePhotos() {
        return "All good. You can see this because you are Authenticated with a Token granted the 'delete:photos' scope";
    }

    @RequestMapping(value = "/**")
    @ResponseBody
    public String anyRequest() {
        return "All good. You can see this because you are Authenticated.";
    }

}