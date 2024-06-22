package com.quizmania.service.Impl;

import com.quizmania.Repositories.QuestionRepository;
import com.quizmania.Repositories.QuizRepository;
import com.quizmania.Repositories.ResultRepository;
import com.quizmania.dto.ApiResponse;
import com.quizmania.entity.*;
import com.quizmania.error.ResourceNotFoundException;
import com.quizmania.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private ResultRepository resultRepository;
    @Override
    public Question addQuestion(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public Set<Question> getAllQuestions() {
        return new LinkedHashSet<>(this.questionRepository.findAll());
    }

    @Override
    public Question updateQuestion(Question question) {
        Optional<Question> retrivedQuestion = this.questionRepository.findById(question.getQuestionId());
        if(retrivedQuestion.isEmpty()) throw new ResourceNotFoundException("Question","with QuestionID",""+question.getQuestionId());
        return this.questionRepository.save(question);
    }

    @Override
    public Question getQuestion(Long questionId) {
        Optional<Question> retrivedQuestion = this.questionRepository.findById(questionId);
        if(retrivedQuestion.isEmpty()) throw new ResourceNotFoundException("Question","with QuestionID",""+questionId);
        return retrivedQuestion.get();
    }

    @Override
    public ApiResponse deleteQuestion(Long questionId) {
        Optional<Question> retrivedQuestion = this.questionRepository.findById(questionId);
        if(retrivedQuestion.isEmpty()) throw new ResourceNotFoundException("Question","with QuestionID",""+questionId);
        this.questionRepository.deleteById(questionId);
        return new ApiResponse("Question Deleted Successfully");
    }

    @Override
    public Set<Question> getQuestionsOfQuizForAdmin(Quiz quiz) {
        Optional<Quiz> retrivedQuiz = this.quizRepository.findById(quiz.getQid());
        if(retrivedQuiz.isEmpty()) throw new ResourceNotFoundException("Questions","of Quiz with QuizID",""+quiz.getQid());
        return this.questionRepository.findByQuiz(quiz);
    }

    @Override
    public List<Question> getQuestionsOfQuiz(Quiz param_quiz) {
        Optional<Quiz> retrivedQuiz = this.quizRepository.findById(param_quiz.getQid());
        if(retrivedQuiz.isEmpty()) throw new ResourceNotFoundException("Questions","of Quiz with QuizID",""+param_quiz.getQid());
        //returning max question based on maxQuestions field in quiz class
        Quiz quiz= this.quizRepository.findById(param_quiz.getQid()).get();
        Set<Question> questionsOfQuiz = quiz.getQuestions();
        List<Question> list= new ArrayList(questionsOfQuiz);
        Collections.shuffle(list);
        if(list.size()>Integer.parseInt(quiz.getNumberOfQuestions())){
            list=list.subList(0,Integer.parseInt(quiz.getNumberOfQuestions()));
        }
//        list.forEach((question)->{
//            question.setAnswer("");
////            List<String> options= question.getOptions();
//            Collections.shuffle(question.getOptions());
//        });
        for(int i=0;i< list.size();i++){
            list.get(i).setAnswer("");
            List<String> options= new ArrayList<>();

            options.add(list.get(i).getOption1());
            options.add(list.get(i).getOption2());
            options.add(list.get(i).getOption3());
            options.add(list.get(i).getOption4());
            Collections.shuffle(options);
            list.get(i).setOptions(options);
        }
        return list;
    }
    @Override
    public HashMap<Object, Object> evaluateQuiz(List<Question> submittedQuestions,Long userId) {
        Double marksObtained=0.0;
        Integer questionsAttempted=0;
        Integer correctAnswers=0;
        for(Question question : submittedQuestions){
            Question tempQuestion= this.get(question.getQuestionId());

            if(tempQuestion.getAnswer().equals(question.getSelectedAnswer())){
                correctAnswers++;
            }
            if(question.getSelectedAnswer()!=null){
                questionsAttempted++;
            }
        }
        marksObtained= (Double.parseDouble(submittedQuestions.get(0).getQuiz().getMaxMarks()) / Double.parseDouble(submittedQuestions.get(0).getQuiz().getNumberOfQuestions()))*correctAnswers;
        HashMap<Object , Object> result = new HashMap<>();

        result.put("marksObtained",marksObtained);
        result.put("questionsAttempted",questionsAttempted);
        result.put("correctAnswers",correctAnswers);

        User user = new User();
        user.setUserId(userId);
        Result result1= new Result();
        result1.setCorrectAnswer(correctAnswers);
        result1.setMarksObtained(marksObtained);
        result1.setNoOfQuestionsAttempted(questionsAttempted);
        SimpleDateFormat formatDate = new SimpleDateFormat(
                "yyyy/MM/dd-HH:mm:ss-z");
        formatDate.setTimeZone(TimeZone.getTimeZone("IST"));
        result1.setDate(formatDate.format(new Date()));
        result1.setQuiz(submittedQuestions.get(0).getQuiz());
        result1.setUser(user);
        result1.setNoOfAttempts(this.resultRepository.countByUserAndQuiz(user,submittedQuestions.get(0).getQuiz())+1);
        this.resultRepository.save(result1);

        return result;
    }

    @Override
    public Question get(Long questionId) {
        return this.questionRepository.getOne(questionId);
    }
}
