package com.quizmania.controller;

import com.quizmania.entity.Quiz;
import com.quizmania.entity.Result;
import com.quizmania.entity.User;
import com.quizmania.service.Impl.ResultServiceImpl;
import com.quizmania.service.ResultService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.ResolutionSyntax;
import java.util.List;

@RestController
@RequestMapping("/result")
@CrossOrigin("*")
public class ResultController {
    @Autowired
    private ResultServiceImpl resultServiceImpl;

    @PostMapping("/add")
    public ResponseEntity<?> addResult(@RequestBody Result result){
        return  new ResponseEntity<>(this.resultServiceImpl.addResult(result), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Result> updateResult(@RequestBody Result result){
        return new ResponseEntity<>(this.resultServiceImpl.updateResult(result),HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteResult(@PathVariable("resultId") Long resultId){
        this.resultServiceImpl.deleteResult(resultId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{resultId}")
    public ResponseEntity<Result> getResult(@PathVariable("resultId") Long resultId){
        return new ResponseEntity<>(this.resultServiceImpl.getResult(resultId),HttpStatus.OK );
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Result>> getAllResults(){
        return new ResponseEntity<>(this.resultServiceImpl.getAllResult(),HttpStatus.OK);
    }

    @GetMapping("/quiz/{quizId}")
    public  ResponseEntity<List<Result>> getResultsOfQuiz(@PathVariable("quizId") Long quizId){
        Quiz quiz = new Quiz();
        quiz.setQid(quizId);
        return new ResponseEntity<>(this.resultServiceImpl.getResultsOfQuiz(quiz),HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Result>> getResultsOfUser(@PathVariable("userId") Long userId){
        User user = new User();
        user.setUserId(userId);
        return new ResponseEntity<>(this.resultServiceImpl.getResultsOfUser(user),HttpStatus.OK);
    }
    @GetMapping("/user/{userId}/quiz/{quizId}")
    public ResponseEntity<List<Result>> getResultsOfUserOfQuiz(@PathVariable("userId") Long userId, @PathVariable("quizId") Long quizId){
        User user= new User();
        user.setUserId(userId);
        Quiz quiz= new Quiz();
        quiz.setQid(quizId);
        return  new ResponseEntity<>(this.resultServiceImpl.getReusltsOfUserOfQuiz(user,quiz),HttpStatus.OK);
    }

    @GetMapping("/marks/getAll")
    public ResponseEntity<List<Result>> getAllResultsByMarks(){
        return new ResponseEntity<>(this.resultServiceImpl.getAllResultOrderByMarksObtainedDesc(),HttpStatus.OK);
    }

    @GetMapping("marks/user/{userId}")
    public ResponseEntity<List<Result>> getResultOfUserByMarks(@PathVariable("userId") Long userId){
        User user = new User();
        user.setUserId(userId);
        return new ResponseEntity<>(this.resultServiceImpl.getResultsOfUserOrderByMarksObtainedDesc(user),HttpStatus.OK);
    }

    @GetMapping("marks/quiz/{quizId}")
    public ResponseEntity<List<Result>> getResultOfQuizByMarks(@PathVariable("quizId") Long quizId){
        Quiz quiz = new Quiz();
        quiz.setQid(quizId);
        return new ResponseEntity<>(this.resultServiceImpl.getResultsOfQuizOrderByMarksObtainedDesc(quiz),HttpStatus.OK);
    }

    @GetMapping("marks/user/{userId}/quiz/{quizId}")
    public ResponseEntity<List<Result>> getResultOfUserOfQuizByMarks(@PathVariable("userId") Long userId, @PathVariable("quizId") Long quizId){
        User user = new User();
        user.setUserId(userId);
        Quiz quiz = new Quiz();
        quiz.setQid(quizId);
        return new ResponseEntity<>(this.resultServiceImpl.getResultsOfUserOfQuizOrderByMarksObtainedDesc(user,quiz),HttpStatus.OK);
    }

    @GetMapping("attempts/user/{userId}/quiz/{quizId}")
    public ResponseEntity<Integer> getCountOfAttemptsOfQuizOfUser(@PathVariable("userId") Long userId, @PathVariable("quizId") Long quizId){
        User user = new User();
        user.setUserId(userId);
        Quiz quiz = new Quiz();
        quiz.setQid(quizId);
        return new ResponseEntity<>(this.resultServiceImpl.getCountOfAttemptsOfQuizOfUser(user,quiz),HttpStatus.OK);
    }

    @GetMapping("leaderboard/quiz/{quizId}")
    public ResponseEntity<List<Result>> getLeaderboardOfQuiz(@PathVariable("quizId") Long quizId){
        Quiz quiz = new Quiz();
        quiz.setQid(quizId);
        return new ResponseEntity<>(this.resultServiceImpl.getLeaderboardOfQuiz(quiz),HttpStatus.OK);
    }
}
