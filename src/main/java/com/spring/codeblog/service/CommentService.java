package com.spring.codeblog.service;

import com.spring.codeblog.model.Comment;

import java.util.List;

public interface  CommentService {
    Comment addComment(Long postId, String autor, String texto, String mediaPath);
    List<Comment> findAllByPostId(Long postId);  // Método para buscar comentários de um post
    Comment likeComment(Long commentId);
}
