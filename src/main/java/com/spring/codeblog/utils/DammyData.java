package com.spring.codeblog.utils;

import com.spring.codeblog.model.Post;
import com.spring.codeblog.repository.CodeblogRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DammyData {

    @Autowired
    CodeblogRepository codeblogRepository;


    //@PostConstruct
    public void savePosts(){

        List<Post> postList = new ArrayList<>();
        Post post1 = new Post();
        post1.setAutor("Michelle Brito");
        post1.setData(LocalDate.now());
        post1.setTitulo("Docker");
        post1.setTexto("Docker é uma plataforma de código aberto que facilita a criação, o gerenciamento e a execução de aplicativos em contêineres. ");

        Post post2 = new Post();
        post2.setAutor("Michelle Brito");
        post2.setData(LocalDate.now());
        post2.setTitulo("API REST");
        post2.setTexto("Uma API REST (Representational State Transfer) é uma interface que permite a comunicação entre sistemas utilizando o protocolo HTTP. ");

        postList.add(post1);
        postList.add(post2);

        for (Post post : postList) {
            Post postSaved = codeblogRepository.save(post);
            System.out.println(postSaved.getId());
        }
    }
}
