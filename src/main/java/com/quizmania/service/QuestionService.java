package com.quizmania.service;

import com.quizmania.dto.ApiResponse;
import com.quizmania.entity.Question;
import com.quizmania.entity.Quiz;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface QuestionService {
    public Question addQuestion(Question question);
    public Set<Question> getAllQuestions();
    public Question updateQuestion(Question question);
    public Question getQuestion(Long questionId);
    public ApiResponse deleteQuestion(Long questionId);

    public List<Question> getQuestionsOfQuiz(Quiz quiz);
    public Set<Question> getQuestionsOfQuizForAdmin(Quiz quiz);

    public HashMap<Object , Object> evaluateQuiz(List<Question> questions,Long UserId);

    public Question get(Long questionId);
}
