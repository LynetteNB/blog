package com.example.blog.services;

import com.example.blog.models.Category;
import com.example.blog.models.Post;
import com.example.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepo;

    @Value("${file-upload-path}")
    private String uploadPath;

    public PostService(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    public Post findOne(long id) {
        return postRepo.findById(id);
    }
    public List<Post> findAll() {
        return (List) postRepo.findAll();
    }
    public Post save(Post post) {
        return postRepo.save(post);
    }
    public void deleteById(Long id) {
        Post post = postRepo.findById(id);
        postRepo.delete(post);
    }
    public String today() {
        LocalDate today = LocalDate.now();
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return months[today.getMonthValue()-1] + " " + today.getDayOfMonth() + ", " + today.getYear();
    }

    public String saveFile(MultipartFile uploadedFile, Model model) {
        String filename = uploadedFile.getOriginalFilename();
        if(filename.trim().equals("")) {
            return null;
        }
        String htmlfilepath = Paths.get("/img/", filename).toString();
        String filepath = Paths.get(uploadPath, filename).toString();
        File destinationFile = new File(filepath);
        try {
            uploadedFile.transferTo(destinationFile);
            model.addAttribute("message", "File successfully uploaded!");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Oops! Something went wrong! " + e);
        }
        return htmlfilepath;
    }


}
