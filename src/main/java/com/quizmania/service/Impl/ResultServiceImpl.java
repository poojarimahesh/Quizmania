package com.quizmania.service.Impl;

import com.quizmania.Repositories.QuizRepository;
import com.quizmania.Repositories.ResultRepository;
import com.quizmania.Repositories.UserRepository;
import com.quizmania.entity.Quiz;
import com.quizmania.entity.Result;
import com.quizmania.entity.User;
import com.quizmania.error.ResourceNotFoundException;
import com.quizmania.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultServiceImpl implements ResultService {
    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public Result addResult(Result result) {
        return this.resultRepository.save(result);
    }

    @Override
    public Result updateResult(Result result) {
        Optional<Result> retrivedResult = this.resultRepository.findById(result.getId());
        if(retrivedResult.isEmpty()) throw new ResourceNotFoundException("Result","with ResultID",""+result.getId());
        return this.resultRepository.save(result);
    }

    @Override
    public void deleteResult(Long resultId) {
        Optional<Result> retrivedResult = this.resultRepository.findById(resultId);
        if(retrivedResult.isEmpty()) throw new ResourceNotFoundException("Result","with ResultID",""+resultId);
        this.resultRepository.deleteById(resultId);
    }

    @Override
    public Result getResult(Long resultId) {
        Optional<Result> retrivedResult = this.resultRepository.findById(resultId);
        if(retrivedResult.isEmpty()) throw new ResourceNotFoundException("Result","with ResultID",""+resultId);
        return this.resultRepository.findById(resultId).get();
    }

    @Override
    public List<Result> getResultsOfUser(User user) {
        Optional<User> retrivedUser = this.userRepository.findById(user.getUserId());
        if(retrivedUser.isEmpty()) throw new ResourceNotFoundException("Results","of User with UserID",""+user.getUserId());
        return this.resultRepository.findByUserOrderByDateDesc(user);
    }

    @Override
    public List<Result> getAllResult() {
        return this.resultRepository.findAll();
    }

    @Override
    public List<Result> getResultsOfQuiz(Quiz quiz) {
        Optional<Quiz> retrivedQuiz = this.quizRepository.findById(quiz.getQid());
        if(retrivedQuiz.isEmpty()) throw new ResourceNotFoundException("Results","of Quiz with QuizID",""+quiz.getQid());
        return this.resultRepository.findByQuizOrderByDateDesc(quiz);
    }

    @Override
    public List<Result> getReusltsOfUserOfQuiz(User user, Quiz quiz) {
        Optional<User> retrivedUser = this.userRepository.findById(user.getUserId());
        if(retrivedUser.isEmpty()) throw new ResourceNotFoundException("Results","of User with UserID",""+user.getUserId());
        Optional<Quiz> retrivedQuiz = this.quizRepository.findById(quiz.getQid());
        if(retrivedQuiz.isEmpty()) throw new ResourceNotFoundException("Results","of Quiz with QuizID",""+quiz.getQid());
        return this.resultRepository.findByUserAndQuizOrderByDateDesc(user,quiz);
    }

    @Override
    public List<Result> getResultsOfUserOrderByMarksObtainedDesc(User user) {
        Optional<User> retrivedUser = this.userRepository.findById(user.getUserId());
        if(retrivedUser.isEmpty()) throw new ResourceNotFoundException("Results","of User with UserID",""+user.getUserId());
        return this.resultRepository.findByUserOrderByMarksObtainedDesc(user);
    }

    @Override
    public List<Result> getResultsOfQuizOrderByMarksObtainedDesc(Quiz quiz) {
        Optional<Quiz> retrivedQuiz = this.quizRepository.findById(quiz.getQid());
        if(retrivedQuiz.isEmpty()) throw new ResourceNotFoundException("Results","of Quiz with QuizID",""+quiz.getQid());
        return this.resultRepository.findByQuizOrderByMarksObtainedDesc(quiz);
    }

    @Override
    public List<Result> getResultsOfUserOfQuizOrderByMarksObtainedDesc(User user, Quiz quiz) {
        Optional<User> retrivedUser = this.userRepository.findById(user.getUserId());
        if(retrivedUser.isEmpty()) throw new ResourceNotFoundException("Results","of User with UserID",""+user.getUserId());
        Optional<Quiz> retrivedQuiz = this.quizRepository.findById(quiz.getQid());
        if(retrivedQuiz.isEmpty()) throw new ResourceNotFoundException("Results","of Quiz with QuizID",""+quiz.getQid());
        return this.resultRepository.findByUserAndQuizOrderByMarksObtainedDesc(user, quiz);
    }

    @Override
    public List<Result> getAllResultOrderByMarksObtainedDesc() {

        return this.resultRepository.findAllByOrderByMarksObtainedDesc();
    }

    @Override
    public Integer getCountOfAttemptsOfQuizOfUser(User user, Quiz quiz) {
        return this.resultRepository.countByUserAndQuiz(user,quiz);
    }

    @Override
    public List<Result> getLeaderboardOfQuiz(Quiz quiz) {
        Optional<Quiz> retrivedQuiz = this.quizRepository.findById(quiz.getQid());
        if(retrivedQuiz.isEmpty()) throw new ResourceNotFoundException("Results","of Quiz with QuizID",""+quiz.getQid());
        return this.resultRepository.findFirst3ByQuizOrderByMarksObtainedDesc(quiz);
    }


}
