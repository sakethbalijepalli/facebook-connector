package com.Facebook.IntentBI.services;

import com.Facebook.IntentBI.models.Page;
import com.Facebook.IntentBI.models.Post;
import com.Facebook.IntentBI.repositories.LikesRepository;
import com.Facebook.IntentBI.repositories.PageRespository;
import com.Facebook.IntentBI.repositories.PostRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@Service
public class PageServiceImpl implements PageService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PageRespository pageRespository;

    @Autowired
    LikesRepository likesRepository;

    @Override
    public Page getAllPosts(String auth_token, String page_id) {
        Gson gson = new Gson();
        String url = "https://graph.facebook.com/v8.0/" + page_id + "/feed" + "?access_token=" + auth_token;
        HttpClient client = HttpClient.newHttpClient();
        Page page = new Page();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            page = gson.fromJson(response.body(), Page.class);
            page.setId(page_id);
            for (Post post : page.getData()) {
                System.out.println(post.getId());
                postRepository.saveAndFlush(post);
            }
            return pageRespository.saveAndFlush(page);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        JsonAdapter<Page> postsJsonAdapter= moshi.adapter(Page.class);
        return page;
    }

    @Override
    public Optional<Page> getPageDetails(String auth_token, String page_id) {
        Gson gson = new Gson();
        String url = "https://graph.facebook.com/v8.0/" + page_id + "?fields=about,category" + "&access_token=" + auth_token;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Page page = gson.fromJson(response.body(), Page.class);
            return pageRespository.findById(page.getId()).map(
                    value -> {
                        value.setCategory(page.getCategory());
                        value.setAbout(page.getAbout());
                        return pageRespository.saveAndFlush(value);
                    }
            );
        } catch (IOException | InterruptedException e) {
            return Optional.empty();
        }
    }

    @Override
    public Page getAllLikes(String auth_token, String page_id) {
//        Gson gson = new Gson();
//        String url = "https://graph.facebook.com/v8.0/" + page_id + "/blocked" + "?access_token=" + auth_token;
//        HttpClient client = HttpClient.newHttpClient();
//        Likes likes = new Likes();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(url))
//                .build();
//
//
//        try {
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//            likes = gson.fromJson(response.body(), Likes.class);
//            likes.setId(page_id);
//            for (Likes.getName(),Likes.getId()) {
//                System.out.println(likes.getName());
//                System.out.println(likes.getId());
//                likesRepository.saveAndFlush(likes);
//            }
//            return pageRespository.saveAndFlush(page);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
////        JsonAdapter<Page> postsJsonAdapter= moshi.adapter(Page.class);
//        return page;
        return new Page();
    }
}