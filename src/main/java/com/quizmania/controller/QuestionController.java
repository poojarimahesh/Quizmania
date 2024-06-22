package com.quizmania.controller;

import com.quizmania.Repositories.QuestionRepository;
import com.quizmania.Repositories.QuizRepository;
import com.quizmania.entity.Question;
import com.quizmania.entity.Quiz;
import com.quizmania.service.Impl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {
    @Autowired
    private QuestionServiceImpl questionServiceImpl;

    @Autowired
    private QuizRepository quizRepository;
    //AddQuestion
    @PostMapping("/add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        return new ResponseEntity<>(this.questionServiceImpl.addQuestion(question), HttpStatus.CREATED );
    }

    //update Question
    @PutMapping("/update")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
        return new ResponseEntity<>(this.questionServiceImpl.updateQuestion(question), HttpStatus.ACCEPTED );
    }

    //get Question
    @GetMapping("/get/{questionId}")
    public ResponseEntity<Question> getQuestion(@PathVariable("questionId") Long questionId){
        return new ResponseEntity<>(this.questionServiceImpl.getQuestion(questionId), HttpStatus.OK );

    }

    //getAllQuestions
    @GetMapping("/getAll")
    public ResponseEntity<Set<Question>> getAllQuestions(){
        return new ResponseEntity<>(this.questionServiceImpl.getAllQuestions(), HttpStatus.OK);
    }

    //DeleteQuestion
    @DeleteMapping("/delete/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("questionId") Long questionId){
        return new ResponseEntity<>(this.questionServiceImpl.deleteQuestion(questionId),HttpStatus.OK);
    }

    //Get questions of quiz
    @GetMapping("/quiz/{qid}")
    public ResponseEntity<List<Question>> getQuestionsOfQuiz(@PathVariable("qid") Long qid){
//        Returning all questions of quiz
//        Quiz quiz = new Quiz();
//        quiz.setQid(qid);
//        return new ResponseEntity<>(this.questionServiceImpl.getQuestionsOfQuiz(quiz),HttpStatus.FOUND);

//        Collections.shuffle(list);
        Quiz quiz = new Quiz();
        quiz.setQid(qid);
        return new ResponseEntity<>(this.questionServiceImpl.getQuestionsOfQuiz(quiz),HttpStatus.OK);

    }
    @GetMapping("/quiz/admin/{qid}")
    public ResponseEntity<List<Question>> getQuestionsOfQuizAdmin(@PathVariable("qid") Long qid) {
//        Returning all questions of quiz
        Quiz quiz = new Quiz();
        quiz.setQid(qid);
        return new ResponseEntity<>(new ArrayList(this.questionServiceImpl.getQuestionsOfQuizForAdmin(quiz)), HttpStatus.OK);
    }



    }
