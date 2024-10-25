package com.spring.codeblog.controller;

import com.spring.codeblog.exception.ResourceNotFoundException;
import com.spring.codeblog.model.Comment;
import com.spring.codeblog.model.Post;
import com.spring.codeblog.service.CodeblogService;
import com.spring.codeblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
public class CommentController {

    @Autowired
    private CodeblogService codeblogService;

    @Autowired
    private CommentService commentService;

    private static final String UPLOAD_DIR = "uploads/";

    // Exibir comentários de um post específico
    @GetMapping("/posts/{postId}/comments")
    public String showComments(@PathVariable Long postId, Model model) {
        Post post = codeblogService.findById(postId);
        List<Comment> comments = commentService.findAllByPostId(postId);
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        return "comments"; // Retorna o template Thymeleaf "comments.html"
    }

    // Adicionar novo comentário a um post, incluindo mídia (imagem ou vídeo)
    @PostMapping("/comments/add")
    public String addComment(@RequestParam Long postId,
                             @RequestParam String autor,
                             @RequestParam String texto,
                             @RequestParam("file") MultipartFile file,
                             RedirectAttributes attributes) {
        String mediaPath = null;

        if (!file.isEmpty()) {
            try {
                String newFileName = UUID.randomUUID() + "_" + file.getOriginalFilename(); // Adiciona UUID ao nome
                Path path = Paths.get(UPLOAD_DIR + newFileName);
                Files.write(path, file.getBytes());
                mediaPath = newFileName;
            } catch (IOException e) {
                attributes.addFlashAttribute("mensagem", "Erro ao fazer upload da mídia.");
                return "redirect:/posts/" + postId + "/comments";
            }
        }

        if (autor.isEmpty() || texto.isEmpty()) {
            attributes.addFlashAttribute("mensagem", "Autor e texto não podem estar vazios.");
            return "redirect:/posts/" + postId + "/comments";
        }

        // Adicionar o comentário com o mediaPath
        commentService.addComment(postId, autor, texto, mediaPath);

        return "redirect:/posts/" + postId + "/comments";
    }

    @PostMapping("/posts/{postId}/comments/{commentId}/like")
    public String likeComment(@PathVariable Long postId,
                              @PathVariable Long commentId,
                              RedirectAttributes attributes) {
        try {
            Comment likedComment = commentService.likeComment(commentId);
            attributes.addFlashAttribute("mensagem", "Comentário curtido com sucesso.");
        } catch (ResourceNotFoundException e) {
            attributes.addFlashAttribute("mensagem", e.getMessage());
        }
        return "redirect:/posts/" + postId + "/comments";
    }
}
