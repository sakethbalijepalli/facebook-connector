package com.Facebook.IntentBI.controller;

import com.Facebook.IntentBI.models.Page;
import com.Facebook.IntentBI.services.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping
@RestController
public class FacebookController {


    @Autowired
    PageService pageService;

//
    @GetMapping("/{page_id}/posts")
    public Optional<Page> getAllPosts(@RequestHeader final String auth_token, @PathVariable final String page_id) {
        pageService.getAllPosts(auth_token,page_id);
        return pageService.getPageDetails(auth_token,page_id);
    }
}
