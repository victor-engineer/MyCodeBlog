package com.spring.codeblog.service.serviceImpl;

import com.spring.codeblog.exception.ResourceNotFoundException;
import com.spring.codeblog.model.Post;
import com.spring.codeblog.repository.CodeblogRepository;
import com.spring.codeblog.service.CodeblogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class CodeblogServiceImpl implements CodeblogService {

    @Autowired
    CodeblogRepository codeblogRepository;

    @Override
    public List<Post> findAll() {
        return codeblogRepository.findAll();
    }

    @Override
    public Post findById(Long id) {
        return codeblogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with ID: " + id));
    }

    @Override
    @Transactional
    public List<Post> searchPosts(String query) {
        return codeblogRepository.findByTituloContainingIgnoreCase(query);
    }

    // Método para salvar um post com mídia
    public Post savePostWithMedia(Post post, MultipartFile mediaFile) {
        String mediaPath = uploadFile(mediaFile);
        post.setMediaPath(mediaPath); // Setar o caminho da mídia no post
        return codeblogRepository.save(post);
    }

    // Método para upload de arquivos
    private String uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            return null; // Se não houver arquivo, retorna null
        }

        // Ajuste o caminho conforme necessário
        String uploadDir = "caminho/para/pasta/uploads/";
        String filePath = uploadDir + file.getOriginalFilename();

        try {
            // Criar diretório se não existir
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            file.transferTo(new File(filePath)); // Salvar o arquivo no diretório
            return filePath; // Retornar o caminho do arquivo
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Em caso de erro, retorna null
        }
    }
}
