package com.Facebook.IntentBI.repositories;

import com.Facebook.IntentBI.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,String> {
}
