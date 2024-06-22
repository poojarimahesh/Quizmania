package com.quizmania.entity;

import com.fasterxml.jackson.databind.node.DoubleNode;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    @Temporal(TemporalType.TIMESTAMP)
    private String date;
    private Integer noOfAttempts;
    private Double marksObtained;
    private Integer noOfQuestionsAttempted;
    private Integer correctAnswer;

    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;


    public Result() {
    }

    public Result(Long id, String date, Integer noOfAttempts, Double marksObtained, Integer noOfQuestionsAttempted, Integer correctAnswer, Quiz quiz, User user) {
        this.id = id;
        this.date = date;
        this.noOfAttempts = noOfAttempts;
        this.marksObtained = marksObtained;
        this.noOfQuestionsAttempted = noOfQuestionsAttempted;
        this.correctAnswer = correctAnswer;
        this.quiz = quiz;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getNoOfAttempts() {
        return noOfAttempts;
    }

    public void setNoOfAttempts(Integer noOfAttempts) {
        this.noOfAttempts = noOfAttempts;
    }

    public Double getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(Double marksObtained) {
        this.marksObtained = marksObtained;
    }

    public Integer getNoOfQuestionsAttempted() {
        return noOfQuestionsAttempted;
    }

    public void setNoOfQuestionsAttempted(Integer noOfQuestionsAttempted) {
        this.noOfQuestionsAttempted = noOfQuestionsAttempted;
    }

    public Integer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Integer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
