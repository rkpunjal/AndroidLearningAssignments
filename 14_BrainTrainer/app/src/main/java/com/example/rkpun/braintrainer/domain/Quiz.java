package com.example.rkpun.braintrainer.domain;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private List<Question> questions = null;
    private int score=0;
    private int currentQuestionIndex=0;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question){
        if(questions == null){
            questions = new ArrayList<Question>();
        }
        questions.add(question);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public void setCurrentQuestionIndex(int currentQuestionIndex) {
        this.currentQuestionIndex = currentQuestionIndex;
    }

    public void incrementQuestionIndex(){
        currentQuestionIndex++;
    }

    public void incrementScore(){
        score++;
    }
}
