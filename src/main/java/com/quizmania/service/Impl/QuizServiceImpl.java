package com.quizmania.service.Impl;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.quizmania.Repositories.CategoryRepository;
import com.quizmania.Repositories.QuizRepository;
import com.quizmania.dto.ApiResponse;
import com.quizmania.entity.Category;
import com.quizmania.entity.Question;
import com.quizmania.entity.Quiz;
import com.quizmania.error.ResourceNotFoundException;
import com.quizmania.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Quiz addQuiz(Quiz quiz) {

        return this.quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {

        Optional<Quiz> retrivedQuiz = this.quizRepository.findById(quiz.getQid());
        if(retrivedQuiz.isEmpty()) throw new ResourceNotFoundException("Quiz","with QuizID",""+quiz.getQid());
        return this.quizRepository.save(quiz);
    }

    @Override
    public Set<Quiz> getQuizzes() {
        return new LinkedHashSet<>(this.quizRepository.findAll());
    }

    @Override
    public Quiz getQuiz(Long quizId) {
        Optional<Quiz> retrivedQuiz = this.quizRepository.findById(quizId);
        if(retrivedQuiz.isEmpty()) throw new ResourceNotFoundException("Quiz","with QuizID",""+quizId);
        return retrivedQuiz.get();
    }

    @Override
    public ApiResponse deleteQuiz(Long quizId) {
        Optional<Quiz> retrivedQuiz = this.quizRepository.findById(quizId);
        if(retrivedQuiz.isEmpty()) throw new ResourceNotFoundException("Quiz","with QuizID",""+quizId);
        this.quizRepository.deleteById(quizId);
        return new ApiResponse("Quiz Deleted Successfully");

    }

    @Override
    public List<Quiz> getQuizzesOfCategory(Category category) {
        Optional<Category> retrivedCategory = this.categoryRepository.findById(category.getCid());
        if(retrivedCategory.isEmpty()) throw new ResourceNotFoundException("Quizzes","of Category with CategoryID",""+category.getCid());
        return this.quizRepository.findByCategory(category);
    }

    @Override
    public List<Quiz> getActiveQuizzes() {
        return this.quizRepository.findByActive(true);
    }

    @Override
    public List<Quiz> getActiveQuizzesOfCategory(Category category) {
        Optional<Category> retrivedCategory = this.categoryRepository.findById(category.getCid());
        if(retrivedCategory.isEmpty()) throw new ResourceNotFoundException("Quizzes","of Category with CategoryID",""+category.getCid());
        return this.quizRepository.findByCategoryAndActive(category,true);
    }
}

