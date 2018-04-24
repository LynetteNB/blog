package com.example.blog.repositories;

import com.example.blog.models.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{
    Category findById(Long id);
    Category findByCategory(String category);

}