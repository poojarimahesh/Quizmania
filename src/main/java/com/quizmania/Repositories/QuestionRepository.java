package com.quizmania.Repositories;

import com.quizmania.entity.Question;
import com.quizmania.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    public Set<Question> findByQuiz(Quiz quiz);

}
