package com.ajinkyabhutkar.quiz_category.repo;

import com.ajinkyabhutkar.quiz_category.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
}
