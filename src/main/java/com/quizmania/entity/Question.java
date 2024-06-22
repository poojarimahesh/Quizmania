package com.quizmania.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long questionId;
    @Column(length = 500)
    private String content;
    @Column(length = 500)
    private String image;
    @Column(length = 500)
    private String option1;
    @Column(length = 500)
    private String option2;
    @Column(length = 500)
    private String option3;
    @Column(length = 500)
    private String option4;
    @Column(length = 500)
    private String answer;

    @Transient
    private List<String> options = new ArrayList<>();
    @Transient
    private String selectedAnswer;

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;
    public Question() {
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
        this.options.add(option1);
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
        this.options.add(option2);
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
        this.options.add(option3);
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {

        this.options.add(option4);
        this.option4 = option4;

    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
