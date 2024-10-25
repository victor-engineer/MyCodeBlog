package com.spring.codeblog.service.serviceImpl;

import com.spring.codeblog.exception.ResourceNotFoundException;
import com.spring.codeblog.model.Comment;
import com.spring.codeblog.model.Post;
import com.spring.codeblog.repository.CommentRepository;
import com.spring.codeblog.service.CommentService; // Importar a interface
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService { // Implementar a interface

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment addComment(Long postId, String autor, String texto, String mediaPath) {
        Comment comment = new Comment();
        comment.setAutor(autor);
        comment.setTexto(texto);
        comment.setMediaPath(mediaPath);
        comment.setPost(new Post(postId)); // Associe o post ao comentário

        return commentRepository.save(comment); // Retornar o comentário salvo
    }

    @Override
    public List<Comment> findAllByPostId(Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @Override
    public Comment likeComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comentário não encontrado"));
        comment.setLikes(comment.getLikes() + 1); // Incrementa os likes
        return commentRepository.save(comment); // Retorna o comentário atualizado
    }
}
