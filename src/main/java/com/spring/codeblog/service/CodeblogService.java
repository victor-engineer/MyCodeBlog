package com.spring.codeblog.service;

import com.spring.codeblog.model.Post;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

public interface CodeblogService {

    List<Post> findAll();
    Post findById(Long id);
    List<Post> searchPosts(String query);
    Post savePostWithMedia(Post post, MultipartFile file);
}
