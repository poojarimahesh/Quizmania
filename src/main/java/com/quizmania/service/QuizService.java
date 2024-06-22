package com.quizmania.service;

import com.quizmania.dto.ApiResponse;
import com.quizmania.entity.Category;
import com.quizmania.entity.Quiz;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface QuizService {
    public Quiz addQuiz(Quiz quiz);
    public Quiz updateQuiz(Quiz quiz);
    public Set<Quiz> getQuizzes();
    public  Quiz getQuiz(Long quizId);
    public ApiResponse deleteQuiz(Long quizId);

    List<Quiz> getQuizzesOfCategory(Category category);

    List<Quiz> getActiveQuizzes();

    List<Quiz> getActiveQuizzesOfCategory(Category category);


}
