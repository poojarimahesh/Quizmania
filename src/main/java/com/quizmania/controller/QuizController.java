package com.quizmania.controller;

import com.quizmania.entity.Category;
import com.quizmania.entity.Question;
import com.quizmania.entity.Quiz;
import com.quizmania.service.Impl.QuestionServiceImpl;
import com.quizmania.service.Impl.QuizServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.OutputKeys;
import java.util.List;
import java.util.Set;

@RestController

@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {
    @Autowired
    private QuizServiceImpl quizServiceImpl;

    @Autowired
    private QuestionServiceImpl questionServiceImpl;

    //addQuiz
    @PostMapping("/add")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz){
        return new ResponseEntity<>(this.quizServiceImpl.addQuiz(quiz), HttpStatus.CREATED);
    }

    //Update quiz
    @PutMapping("/update")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz){
        return new ResponseEntity<>(this.quizServiceImpl.updateQuiz(quiz), HttpStatus.ACCEPTED );
    }

    //getQuiz
    @GetMapping("/get/{quizId}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable("quizId") Long quizId){
        return new ResponseEntity<>(this.quizServiceImpl.getQuiz(quizId),HttpStatus.OK );
    }
    //delete Quiz
    @DeleteMapping("/delete/{quizId}")
    public ResponseEntity<?> deleteQuiz(@PathVariable("quizId") long quizId){

        return new ResponseEntity<>(this.quizServiceImpl.deleteQuiz(quizId),HttpStatus.OK);
    }

    //getAllQuizzes
    @GetMapping("/getAll")
    public ResponseEntity<Set<Quiz>> getAllQuizzes(){
        return new ResponseEntity<>(this.quizServiceImpl.getQuizzes(), HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getQuizzesOfCategory(@PathVariable("categoryId") Long categoryId){
        Category category= new Category();
        category.setCid(categoryId);
        return new ResponseEntity<>(this.quizServiceImpl.getQuizzesOfCategory(category), HttpStatus.OK);
    }

    @GetMapping("/getAllActive")
    public ResponseEntity<?> getActiveQuizzes(){
        return new ResponseEntity<>(this.quizServiceImpl.getActiveQuizzes(),HttpStatus.OK);
    }

    @GetMapping("/getActive/category/{categoryId}")
    public ResponseEntity<?> getActiveQuizzesOfCategory(@PathVariable("categoryId") Long categoryId){
        Category category= new Category();
        category.setCid(categoryId);
        return new ResponseEntity<>(this.quizServiceImpl.getActiveQuizzesOfCategory(category),HttpStatus.OK);
    }


    @PostMapping("/evaluateQuiz/{userId}")
    public ResponseEntity<?> evaluateQuiz(@RequestBody List<Question> submittedQuestions, @PathVariable("userId") Long userId ){

        return ResponseEntity.ok(this.questionServiceImpl.evaluateQuiz(submittedQuestions,userId));
    }
}
