package com.quizmania.Repositories;

import com.quizmania.entity.Quiz;
import com.quizmania.entity.Result;
import com.quizmania.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ResultRepository extends JpaRepository<Result,Long> {
    public List<Result> findByUserOrderByDateDesc(User user);
    public List<Result> findByQuizOrderByDateDesc(Quiz quiz);

    public List<Result> findByUserAndQuizOrderByDateDesc(User user, Quiz quiz);
    public List<Result> findByUserOrderByMarksObtainedDesc(User user);
    public List<Result> findByQuizOrderByMarksObtainedDesc(Quiz quiz);

    public List<Result> findFirst3ByQuizOrderByMarksObtainedDesc(Quiz quiz);
    public List<Result> findByUserAndQuizOrderByMarksObtainedDesc(User user, Quiz quiz);

    public List<Result> findAllByOrderByMarksObtainedDesc();


    public Integer countByUserAndQuiz(User user,Quiz quiz);
}
