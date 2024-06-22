package com.quizmania.service;

import com.quizmania.entity.Quiz;
import com.quizmania.entity.Result;
import com.quizmania.entity.User;

import java.util.List;

public interface ResultService {
    public Result addResult(Result result);
    public Result updateResult(Result result);
    public void deleteResult(Long resultId);

    public Result getResult(Long resultId);

    public List<Result> getResultsOfUser(User user);
    public List<Result> getAllResult();

    public List<Result> getResultsOfQuiz(Quiz quiz);
    public List<Result> getReusltsOfUserOfQuiz(User user, Quiz quiz);
    public List<Result> getResultsOfUserOrderByMarksObtainedDesc(User user);
    public List<Result> getResultsOfQuizOrderByMarksObtainedDesc(Quiz quiz);

    public List<Result> getResultsOfUserOfQuizOrderByMarksObtainedDesc(User user, Quiz quiz);

    public List<Result> getAllResultOrderByMarksObtainedDesc();

    public Integer getCountOfAttemptsOfQuizOfUser(User user,Quiz quiz);

    public List<Result> getLeaderboardOfQuiz(Quiz quiz);

}
