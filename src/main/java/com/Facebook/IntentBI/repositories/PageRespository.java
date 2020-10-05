package com.Facebook.IntentBI.repositories;

import com.Facebook.IntentBI.models.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PageRespository extends JpaRepository<Page,String> {
    Page saveAndFlush();
}
