package com.spring.codeblog.controller;

import com.spring.codeblog.model.Post;
import com.spring.codeblog.service.CodeblogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class CodeblogController {

    @Autowired
    CodeblogService codeblogService;

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ModelAndView getPosts() {
        ModelAndView mv = new ModelAndView("posts");
        List<Post> posts = codeblogService.findAll();
        mv.addObject("posts", posts);
        return mv;
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public ModelAndView getPostDetails(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("postDetails");
        Post post = codeblogService.findById(id);
        mv.addObject("post", post);
        return mv;
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.GET)
    public String getPostForm(Model model) {
        Post post = new Post(); // Criando um novo objeto Post
        model.addAttribute("post", post); // Adicionando o objeto ao modelo
        return "postForm"; // Retornando a view
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.POST)
    public String savePost(@Valid Post post, BindingResult result,
                           @RequestParam("file") MultipartFile file, // Adiciona o parâmetro para o arquivo
                           RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "verifique se os campos obrigatórios foram preenchidos");
            return "redirect:/newpost";
        }

        post.setData(LocalDate.now());
        codeblogService.savePostWithMedia(post, file); // Chama o serviço para salvar o post com a mídia
        return "redirect:/posts";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchPosts(@RequestParam String query) { // Método de busca
        ModelAndView mv = new ModelAndView("posts");
        List<Post> posts = codeblogService.searchPosts(query); // Busca posts
        mv.addObject("posts", posts);
        mv.addObject("searchQuery", query); // Adiciona a query de busca ao modelo
        return mv;
    }
}
