package com.quizmania.Repositories;

import com.quizmania.entity.Category;
import com.quizmania.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
    public List<Quiz> findByCategory(Category category);
    public List<Quiz> findByActive(boolean active);
    public List<Quiz> findByCategoryAndActive(Category category, boolean active);
}
