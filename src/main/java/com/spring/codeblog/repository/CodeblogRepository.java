package com.spring.codeblog.repository;

import com.spring.codeblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CodeblogRepository extends JpaRepository<Post, Long> {
    List<Post> findByTituloContainingIgnoreCase(String titulo); // método de buscar posts pelo titulo
}

