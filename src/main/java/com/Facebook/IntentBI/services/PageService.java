package com.Facebook.IntentBI.services;

import com.Facebook.IntentBI.models.Page;

import java.util.Optional;

public interface PageService {
    Page getAllPosts(String auth_token, String page_id);
    Optional<Page> getPageDetails(String auth_token, String page_id);
    Page getAllLikes(String auth_token, String page_id);



}
